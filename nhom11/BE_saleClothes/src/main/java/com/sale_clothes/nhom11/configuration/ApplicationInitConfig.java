package com.sale_clothes.nhom11.configuration;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.enums.Role;
import com.sale_clothes.nhom11.repository.KhachHangRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder  passwordEncoder;
    @Autowired
    public ApplicationInitConfig(PasswordEncoder pass){
        this.passwordEncoder = pass;
    }
    @Bean
    ApplicationRunner applicationRunner(KhachHangRepository khachHangRepository) {
        return args -> {
            if (khachHangRepository.findById("admin").isEmpty()) {
                HashSet<String> roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                KhachHang admin = KhachHang.builder()
                        .khUserName("admin")
                        .khPassWord(passwordEncoder.encode("admin"))
                        .khTen("hoang")
                        .khEmail("tranhuyyhoang2004@gmail.com")
                        .roles(roles)
                        .build();
                khachHangRepository.save(admin);
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Thêm interceptor để tự động thêm header Authorization cho Qdrant
        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public org.springframework.http.client.ClientHttpResponse intercept(
                    HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

                request.getHeaders().add("api-key", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2Nlc3MiOiJtIn0.hsgcArNt0TJcQY8vUPYMu_Ts8991M_aLJG5w7sBbylo");
                return execution.execute(request, body);
            }
        });

        return restTemplate;
    }
}
