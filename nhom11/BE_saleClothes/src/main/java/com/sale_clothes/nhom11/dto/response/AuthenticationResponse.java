package com.sale_clothes.nhom11.dto.response;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private boolean authenticated;
}
