package com.sale_clothes.nhom11.service.impl;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.dto.request.AuthenticationRequest;
import com.sale_clothes.nhom11.dto.request.IntrospectRequest;
import com.sale_clothes.nhom11.dto.response.AuthenticationResponse;
import com.sale_clothes.nhom11.dto.response.IntrospectResponse;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.exception.AppException;
import com.sale_clothes.nhom11.exception.ErrorCode;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.repository.KhachHangRepository;
import lombok.experimental.NonFinal;

import netscape.javascript.JSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.ErrorManager;

@Service
public class AuthenticationService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;

    //Generate Json web token
    public String generateToken(String userName) {
        // Generate header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);


        // Generate payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userName) // Chứa định danh người dùng, thường là username, userId
                .issuer("localhost") //là claim chỉ định ai đã phát hành token này, thường là tên máy chủ hoặc dịch vụ phát hành
                .issueTime(new Date()) //là claim ghi lại thời điểm token được phát hành, ở dây là lấy thời gian hiện tại
                .expirationTime(new Date( //expirationTime  là claim chỉ định thời gian mà token sẽ hết hạn
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli() //Instant.now() lấy thời gian hiện tại,
                        // sau đó .plus(1, ChronoUnit.HOURS) sẽ cộng thêm 1 giờ vào thời gian hiện tại, tạo ra một thời điểm tương lai là 1 giờ sau.
                ))
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
            // tạo đôi tượng để xác thực chữ ký của token
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            //Phân tích chuỗi token thành một đối tượng SignedJWT
            SignedJWT signedJWT = SignedJWT.parse(token);

            //Lấy thời gian hết hạn từ claims của SignedJWT
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            //Kiếm tra tinhs hợp lệ của token
            boolean verified = signedJWT.verify(verifier);


            return  IntrospectResponse.builder()
                    .valid(verified && expiryTime.after(new Date())) // kiểm tra chữ ký và so sánh thời gian hết hạn với thời thơi hiện tại
                    .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        KhachHang khachHang = khachHangRepository.findById(authenticationRequest.getUserName())
                .orElseThrow(() -> new NotFoundException("Username không tồn tại!"));

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassWord(), khachHang.getKhPassWord());
        if(!authenticated)
           throw new AppException(ErrorCode.UNAUTHENTICATED);
        String token = generateToken(authenticationRequest.getUserName());


        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

}
