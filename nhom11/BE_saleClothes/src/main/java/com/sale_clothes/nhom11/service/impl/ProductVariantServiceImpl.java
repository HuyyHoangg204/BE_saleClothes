package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.mapper.ProductVariantMapper;
import com.sale_clothes.nhom11.repository.ProductVariantRepository;
import com.sale_clothes.nhom11.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private ProductVariantMapper productVariantMapper;
    @Override
    public ProductVariantDTO create(ProductVariantDTO dto) {
        ProductVariant productVariant = productVariantMapper.mapToProductVariant(dto);
        ProductVariant savedProductVariant = productVariantRepository.save(productVariant);
        return productVariantMapper.mapToProductVariantDTO(savedProductVariant);
    }

    @Override
    public List<ProductVariantDTO> getAll() {
        return null;
    }

    @Override
    public ProductVariantDTO getById(int id) {
        return null;
    }

    @Override
    public ProductVariantDTO update(int id, ProductVariantDTO dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
