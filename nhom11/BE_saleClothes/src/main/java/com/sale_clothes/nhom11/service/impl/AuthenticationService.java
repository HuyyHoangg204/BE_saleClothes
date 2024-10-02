package com.sale_clothes.nhom11.service.impl;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.dto.request.IntrospectRequest;
import com.sale_clothes.nhom11.dto.request.LogoutRequest;
import com.sale_clothes.nhom11.dto.request.RefreshRequest;
import com.sale_clothes.nhom11.dto.response.AuthenticationResponse;
import com.sale_clothes.nhom11.dto.response.IntrospectResponse;
import com.sale_clothes.nhom11.entity.InvalidatedToken;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.exception.AppException;
import com.sale_clothes.nhom11.exception.ErrorCode;
import com.sale_clothes.nhom11.mapper.KhachHangMapper;
import com.sale_clothes.nhom11.repository.InvalidatedTokenRepository;
import com.sale_clothes.nhom11.repository.KhachHangRepository;
import lombok.experimental.NonFinal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;

    //Generate Json web token
    public String generateToken(KhachHangDTO khachHangDTO) {
        // Generate header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);


        // Generate payload

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(khachHangDTO.getKhUserName()) // Chứa định danh người dùng, thường là username, userId
                .issuer("localhost") //là claim chỉ định ai đã phát hành token này, thường là tên máy chủ hoặc dịch vụ phát hành
                .issueTime(new Date()) //là claim ghi lại thời điểm token được phát hành, ở dây là lấy thời gian hiện tại
                .expirationTime(new Date( //expirationTime  là claim chỉ định thời gian mà token sẽ hết hạn
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli() //Instant.now() lấy thời gian hiện tại,
                                                                         // sau đó .plus(1, ChronoUnit.HOURS) sẽ cộng thêm 1 giờ vào thời gian hiện tại, tạo ra một thời điểm tương lai là 1 giờ sau.
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(khachHangDTO))
                .build();
        //Tạo một đối tượng Payload từ JWTClaimsSet đã được chuyển đổi thành JSONObject
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        //Tạo một đối tượng JWSObject bằng cách kết hợp header và payload
        JWSObject jwsObject = new JWSObject(header,payload);

        //generate signature
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes())); //Ký JWSObject bằng cách sử dụng MACSigner và khóa bí mật
            return jwsObject.serialize(); //Chuyển đổi JWSObject đã ký thành một chuỗi JWT hoàn chỉnh để có thể sử dụng.
        } catch (JOSEException e) { //Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình ký.
            throw new RuntimeException(e);
        }

    }

    // Validate jwt
    public IntrospectResponse introspectResponse(IntrospectRequest introspectRequest) throws JOSEException,ParseException{
        //  Get token
            String token = introspectRequest.getToken();
           try {
               verifyToken(token);
           } catch (AppException ex) {
               return IntrospectResponse.builder()
                       .valid(false)
                       .build();
           }


            return  IntrospectResponse.builder()
                    .valid(true) // kiểm tra chữ ký và so sánh thời gian hết hạn với thời thơi hiện tại
                    .build();

    }

    public AuthenticationResponse authenticate(KhachHangDTO khachHangDTO) {
        KhachHang khachHang = khachHangRepository.findById(KhachHangMapper.mapToKhachHang(khachHangDTO).getKhUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));

        boolean authenticated = passwordEncoder.matches(KhachHangMapper.mapToKhachHang(khachHangDTO).getKhPassWord(), khachHang.getKhPassWord());
        if(!authenticated)
           throw new AppException(ErrorCode.PASSWORD_FAILED);

        String token = generateToken(khachHangDTO);


        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String buildScope(KhachHangDTO khachHangDTO) {

        Optional<KhachHang> khachHang = khachHangRepository.findById(khachHangDTO.getKhUserName());

        Set<String> roles = khachHang.get().getRoles();
        StringBuilder scopeBuilder = new StringBuilder();
        if (!roles.isEmpty()) {
            for (String role : roles) {
                scopeBuilder.append(role + " ");
            }
        }
        return scopeBuilder.toString().trim(); // Xóa khoảng trắng thừa ở cuối
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        //Phân tích chuỗi token thành một đối tượng SignedJWT
        SignedJWT signedJWT = SignedJWT.parse(token);

        //Lấy thời gian hết hạn từ claims của SignedJWT
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        //Kiếm tra tinhs hợp lệ của token
        boolean verified = signedJWT.verify(verifier);

        if(!(verified && expiryTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signJWT = verifyToken(request.getToken());

        var jit = signJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);

        var username = signJWT.getJWTClaimsSet().getSubject();
        var khachHang = khachHangRepository.findById(username).orElseThrow(
                () -> new AppException(ErrorCode.UNAUTHENTICATED)
        );
        String token = generateToken(KhachHangMapper.mapToKhachHangDTO(khachHang));


        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {

        var signToken = verifyToken(request.getToken());

        String jwtTokenId = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jwtTokenId)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }
}
