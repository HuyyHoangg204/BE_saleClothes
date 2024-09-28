package com.sale_clothes.nhom11.controller;


import com.nimbusds.jose.JOSEException;
import com.sale_clothes.nhom11.dto.request.AuthenticationRequest;
import com.sale_clothes.nhom11.dto.request.IntrospectRequest;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.AuthenticationResponse;
import com.sale_clothes.nhom11.dto.response.IntrospectResponse;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<AuthenticationResponse> authenticationResponse(@RequestBody AuthenticationRequest authenticationRequest) {
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
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

}
