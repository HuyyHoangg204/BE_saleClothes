package com.sale_clothes.nhom11.configuration;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.enums.Role;
import com.sale_clothes.nhom11.repository.KhachHangRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig implements WebMvcConfigurer {
    @Autowired
    PasswordEncoder passwordEncoder;

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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Allow your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow HTTP methods
                .allowedHeaders("*") // Allow all headers
        ;

    }
}
