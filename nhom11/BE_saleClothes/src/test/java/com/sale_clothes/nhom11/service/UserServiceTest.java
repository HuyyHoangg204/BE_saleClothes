package com.sale_clothes.nhom11.service;


import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.repository.KhachHangRepository;
import com.sale_clothes.nhom11.service.impl.KhachHangServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private KhachHangServiceImpl khachHangService;

    @MockBean
    private KhachHangRepository khachHangRepository;

    private KhachHangDTO request;
    private KhachHangDTO response;

    private KhachHang khachHang;

    @BeforeEach
    public void initData() {
        request = KhachHangDTO.builder()
                .khUserName("demotestcase")
                .khPassWord("huyhoang2004")
                .khTen("Hoang")
                .khEmail("ljshfdgihf@gmail.com")
                .build();
        response = KhachHangDTO.builder()
                .khUserName("demotestcase")
                .khTen("Hoang")
                .khEmail("ljshfdgihf@gmail.com")
                .build();
        khachHang = KhachHang.builder()
                .khUserName("demotestcase")
                .khPassWord("huyhoang2004")
                .khTen("Hoang")
                .khEmail("ljshfdgihf@gmail.com")
                .build();
    }

    @Test
    public void createUser_validRequest_success() {
        //GIVEN
        Mockito.when(khachHangRepository.existsByKhUserName(Mockito.anyString())).thenReturn(false);
        Mockito.when(khachHangRepository.existsByKhEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(khachHangRepository.save(Mockito.any())).thenReturn(khachHang);

        // WHEN
        var khachHangResponse = khachHangService.createKhachHang(request);

        // THEN
        assertThat(khachHangResponse.getKhUserName()).isEqualTo("demotestcase");
        assertThat(khachHangResponse.getKhPassWord()).isEqualTo("huyhoang2004");
        assertThat(khachHangResponse.getKhEmail()).isEqualTo("ljshfdgihf@gmail.com");

    }
}
