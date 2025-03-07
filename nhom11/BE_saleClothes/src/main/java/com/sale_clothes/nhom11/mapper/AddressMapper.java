package com.sale_clothes.nhom11.mapper;


import com.sale_clothes.nhom11.dto.AddressDTO;
import com.sale_clothes.nhom11.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(source = "userName", target = "khachHang.khUserName")
    Address mapToAddress(AddressDTO addressDTO);

    @Mapping(source = "khachHang.khUserName", target = "userName")
    AddressDTO mapToAddressDTO(Address address);
}
