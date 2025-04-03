package com.sale_clothes.nhom11.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.mapper.ProductVariantMapper;
import com.sale_clothes.nhom11.repository.ProductVariantRepository;
import com.sale_clothes.nhom11.repository.SanPhamRepository;
import com.sale_clothes.nhom11.service.ProductVariantService;

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
    public ProductVariantDTO getById(Integer id) {
        return null;
    }

    @Override
    public void update(Integer id, ProductVariantDTO dto) {
        Optional<ProductVariant> productVariant = productVariantRepository.findById(id);
        ProductVariant productVariant1 = productVariantMapper.mapToProductVariant(dto);
        if (!productVariant.isPresent()) {
            throw new RuntimeException("Product variant not found");
        } else {
            ProductVariant productVariant2 = productVariant.get();
            productVariant2.setSize(productVariant1.getSize());
            productVariant2.setStockQuantity(productVariant1.getStockQuantity());
            productVariant2.setColor(productVariant1.getColor());
            productVariantRepository.save(productVariant2);
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<ProductVariant> productVariant = productVariantRepository.findById(id);
        if (!productVariant.isPresent()) {
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
        for (ProductVariant productVariant : productVariantList) {
            System.out.println(productVariant);
            productVariantDTOList.add(productVariantMapper.mapToProductVariantDTO(productVariant));
        }
        return productVariantDTOList;
    }
}
