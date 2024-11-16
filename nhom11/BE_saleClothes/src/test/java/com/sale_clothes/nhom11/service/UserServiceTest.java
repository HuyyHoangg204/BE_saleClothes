package com.sale_clothes.nhom11.service;


import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.exception.AppException;
import com.sale_clothes.nhom11.repository.KhachHangRepository;
import com.sale_clothes.nhom11.service.impl.KhachHangServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        //GIVEN: Khi người dùng và email chưa tồn tại
        Mockito.when(khachHangRepository.existsByKhUserName(Mockito.anyString())).thenReturn(false);
        Mockito.when(khachHangRepository.existsByKhEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(khachHangRepository.save(Mockito.any())).thenReturn(khachHang);
        // WHEN: Tạo người dùng
        var khachHangResponse = khachHangService.createKhachHang(request);

        // THEN: Kiểm tra thông tin người dùng đã được tạo đúng
        assertThat(khachHangResponse.getKhUserName()).isEqualTo("demotestcase");
        assertThat(khachHangResponse.getKhPassWord()).isEqualTo("huyhoang2004");
        assertThat(khachHangResponse.getKhEmail()).isEqualTo("ljshfdgihf@gmail.com");
    }
    @Test
    public void createUser_userAlreadyExists_throwsException() {
        // GIVEN: Khi tên người dùng đã tồn tại
        Mockito.when(khachHangRepository.existsByKhUserName(Mockito.anyString())).thenReturn(true);
        Mockito.when(khachHangRepository.existsByKhEmail(Mockito.anyString())).thenReturn(false);

        // WHEN & THEN: Kiểm tra lỗi được ném ra khi tên người dùng đã tồn tại
        assertThrows(AppException.class, () -> khachHangService.createKhachHang(request));
    }
    @Test
    public void createUser_emailAlreadyExists_throwsException() {
        // GIVEN: Khi email đã tồn tại
        Mockito.when(khachHangRepository.existsByKhUserName(Mockito.anyString())).thenReturn(false);
        Mockito.when(khachHangRepository.existsByKhEmail(Mockito.anyString())).thenReturn(true);

        // WHEN & THEN: Kiểm tra lỗi được ném ra khi email đã tồn tại
        assertThrows(AppException.class, () -> khachHangService.createKhachHang(request));
    }
}
