package com.sale_clothes.nhom11.service.impl;


import com.sale_clothes.nhom11.dto.AddressDTO;
import com.sale_clothes.nhom11.entity.Address;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.mapper.AddressMapper;
import com.sale_clothes.nhom11.repository.AddressRepository;
import com.sale_clothes.nhom11.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IService<AddressDTO, Long> {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public AddressDTO create(AddressDTO dto) {
        Address address = addressRepository.save(addressMapper.mapToAddress(dto));
        return addressMapper.mapToAddressDTO(address);
    }

    public List<AddressDTO> getAllAddressByUsername(String username) {
        List<AddressDTO> addressDTOS = new ArrayList<>();
        List<Address> addressList = addressRepository.findAllByUsername(username);

        for(Address address : addressList) {
            addressDTOS.add(addressMapper.mapToAddressDTO(address));
        }
        return  addressDTOS;
    }

    @Override
    public List<AddressDTO> getAll() {
        List<AddressDTO> addressDTOS = new ArrayList<>();
        List<Address> addressList = addressRepository.findAll();

        for (Address address : addressList) {
            AddressDTO addressDTO = addressMapper.mapToAddressDTO(address);
            addressDTOS.add(addressDTO);
        }
        return addressDTOS;
    }

    @Override
    public AddressDTO getById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        AddressDTO addressDTO = new AddressDTO();
        if(address.isPresent()) {
            addressDTO = addressMapper.mapToAddressDTO(address.get());
            return addressDTO;
        } else {
            throw new NotFoundException("Address " + id + " does not exist");
        }

    }

    @Override

    public void update(Long id, AddressDTO dto) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()) {
            Address address1 = address.get();
            address1.setDetailAddress(dto.getDetailAddress());
            address1.setTypeAddress(dto.isTypeAddress());
            address1.setDistrict(dto.getDistrict());
            address1.setProvince(dto.getProvince());
            address1.setVillage(dto.getVillage());
            address1.setFullName(dto.getFullName());
            address1.setPhoneNumber(dto.getPhoneNumber());
            addressRepository.save(address1);
        }
    }

    @Override
    public void delete(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Địa chỉ không tồn tại"));
       addressRepository.delete(address);
    }

    public void deleteByUserName(String username, Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Địa chỉ không tồn tại"));
        addressRepository.deleteByUsernameAndId(username, id);
    }
}
