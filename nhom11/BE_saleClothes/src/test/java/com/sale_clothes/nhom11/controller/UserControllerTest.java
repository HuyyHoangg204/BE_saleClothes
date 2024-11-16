package com.sale_clothes.nhom11.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.service.impl.KhachHangServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KhachHangServiceImpl khachHangService;

    private KhachHangDTO request;
    private KhachHangDTO response;

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
    }


    //Test api co hoat dong dung khi request hop le khong
    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();

        String content = objectMapper.writeValueAsString(request);

        Mockito.when(khachHangService.createKhachHang(ArgumentMatchers.any()))
                        .thenReturn(response);
        //WHEN and THEN

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/add-khachhang")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.khUserName")
                        .value("demotestcase"))
        ;
    }

    //Test api co xy ly dung khi request khong hop le(username khong duoi 5 ky tu)
    @Test
    void createUser_usernameInvalid_failed() throws Exception {
        // GIVEN
        request.setKhUserName("demo");
        ObjectMapper objectMapper = new ObjectMapper();

        String content = objectMapper.writeValueAsString(request);


        //WHEN and THEN

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/add-khachhang")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(1003))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Username không dưới 5 ký tụ"));
    }
    @Test
    void createUser_invalidPassword_failed() throws Exception {
        // GIVEN: Password không đủ dài (dưới 8 ký tự)
        request.setKhPassWord("123456");

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        // WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/add-khachhang")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())  // Kiểm tra mã lỗi 400
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1003))  // Kiểm tra mã lỗi
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Password không dưới 8 ký tự!"));
    }

    @Test
    void createUser_invalidEmail_failed() throws Exception {
        // GIVEN: Email không hợp lệ
        request.setKhEmail("invalid-email");

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        // WHEN and THEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/add-khachhang")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())  // Kiểm tra mã lỗi 400
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1003))  // Kiểm tra mã lỗi
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Email không hợp lệ!"));
    }




}
