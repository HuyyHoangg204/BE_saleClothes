package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.AddressDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.AddressServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/add")
    @PreAuthorize("#addressDTO.userName == authentication.name or hasRole('ADMIN')")
    public ApiResponse<AddressDTO> addAddress(@RequestBody @Valid AddressDTO addressDTO) {
        AddressDTO addressDTO1 = addressService.create(addressDTO);
        return ApiResponse.<AddressDTO>builder()
                .result(addressDTO1)
                .build();
    }

    @PutMapping ("/update/{id}")
    @PreAuthorize("#addressDTO.userName == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<String> updateAddress(@RequestBody @Valid AddressDTO addressDTO,
                                        @PathVariable Long id) {
         try {
             addressService.update(id, addressDTO);
             return ResponseEntity.ok("Cập nhật thành công!");
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy địa chỉ!");
         }
    }

    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ApiResponse<List<AddressDTO>> getAllAddressByUserName(@PathVariable String username) {
        List<AddressDTO> addressDTOS = addressService.getAllAddressByUsername(username);
        return ApiResponse.<List<AddressDTO>>builder().result(addressDTOS).build();
    }

    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ApiResponse<String> deleteAddressByUsername(@Param("username") String username, @Param("id") Long id) {
        addressService.deleteByUserName(username, id);
        return ApiResponse.<String>builder()
                .message("Delete address successfully")
                .build();
    }

}
