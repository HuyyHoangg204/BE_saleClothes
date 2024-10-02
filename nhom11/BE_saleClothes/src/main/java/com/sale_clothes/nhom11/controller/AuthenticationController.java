package com.sale_clothes.nhom11.controller;


import com.nimbusds.jose.JOSEException;
import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.dto.request.AuthenticationRequest;
import com.sale_clothes.nhom11.dto.request.IntrospectRequest;
import com.sale_clothes.nhom11.dto.request.LogoutRequest;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.AuthenticationResponse;
import com.sale_clothes.nhom11.dto.response.IntrospectResponse;
import com.sale_clothes.nhom11.entity.InvalidatedToken;
import com.sale_clothes.nhom11.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticationResponse(@RequestBody KhachHangDTO khachHangDTO) {
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(khachHangDTO);
        response.setResult(authenticationResponse);
            return response;

    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticationResponse(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> response = new ApiResponse<>();
        IntrospectResponse introspectResponse = authenticationService.introspectResponse(introspectRequest);
        response.setResult(introspectResponse);
        return response;

    }

    @PostMapping("/logout")
    public ApiResponse<InvalidatedToken> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<InvalidatedToken>builder()
                .build();
    }
}
