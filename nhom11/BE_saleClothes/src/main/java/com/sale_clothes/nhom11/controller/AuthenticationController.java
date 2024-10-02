package com.sale_clothes.nhom11.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;
import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.dto.request.IntrospectRequest;
import com.sale_clothes.nhom11.dto.request.LogoutRequest;
import com.sale_clothes.nhom11.dto.request.RefreshRequest;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.AuthenticationResponse;
import com.sale_clothes.nhom11.dto.response.IntrospectResponse;
import com.sale_clothes.nhom11.entity.InvalidatedToken;
import com.sale_clothes.nhom11.service.impl.AuthenticationService;

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
    public ApiResponse<IntrospectResponse> authenticationResponse(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> response = new ApiResponse<>();
        IntrospectResponse introspectResponse = authenticationService.introspectResponse(introspectRequest);
        response.setResult(introspectResponse);
        return response;
    }

    @PostMapping("/logout")
    public ApiResponse<InvalidatedToken> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<InvalidatedToken>builder().build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        AuthenticationResponse authenticationResponse = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationResponse)
                .build();
    }
}
