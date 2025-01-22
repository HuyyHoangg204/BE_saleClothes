package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.exception.AppException;
import com.sale_clothes.nhom11.mapper.ProductVariantMapper;
import com.sale_clothes.nhom11.repository.ProductVariantRepository;
import com.sale_clothes.nhom11.repository.SanPhamRepository;
import com.sale_clothes.nhom11.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

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
        Optional<ProductVariant> productVariant = productVariantRepository.findById(id);
        if(!productVariant.isPresent()) {
            throw new RuntimeException("Product variant not found");
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Optional<ProductVariant> productVariant = productVariantRepository.findById(id);
        if(!productVariant.isPresent()) {
            throw new RuntimeException("Product variant not found");
        }

        productVariantRepository.deleteById(id);
    }

    @Override
    public List<ProductVariantDTO> getAllProductVariantsByProductID(int id) {
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);
        if (!sanPham.isPresent()) {
            throw new RuntimeException("Product not found with productID: " + id);
        }

        List<ProductVariant> productVariantList = productVariantRepository.getAllByProductID(id);
        List<ProductVariantDTO> productVariantDTOList = new ArrayList<ProductVariantDTO>();
        for(ProductVariant productVariant : productVariantList) {
            System.out.println(productVariant);
            productVariantDTOList.add(productVariantMapper.mapToProductVariantDTO(productVariant));
        }
        return productVariantDTOList;
    }
}
