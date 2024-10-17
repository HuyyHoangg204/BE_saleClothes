package com.sale_clothes.nhom11.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import lombok.experimental.NonFinal;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.signerKey}")
    @NonFinal
    private String signerKey;

    @Autowired
    private CustomJwtDecoder jwtDecoder;

    private final String[] PUBLIC_ENDPOINT = {
        "/api/add-khachhang", "/auth/login", "/auth/introspect", "/auth/logout", "/auth/refresh", "/images/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // Phân quyền truy cập
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINT).permitAll()
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
                .anyRequest()
                .authenticated());
        // OAuth2 Resource Server với JWT, xử lý các yêu cầu có chứa JWT token
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(jwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())) // convert scopes to roles
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())); // handle exception 401 unauthorized

        // vô hiệu hóa CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    // convert scope -> role
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    // Cấu hình JWT được giải mã
    //    @Bean
    //    JwtDecoder jwtDecoder() {
    //        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
    //        return NimbusJwtDecoder
    //                .withSecretKey(secretKeySpec)
    //                .macAlgorithm(MacAlgorithm.HS512)
    //                .build();
    //    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
