package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.VariantDTO;
import com.sale_clothes.nhom11.dto.response.ProductResponseDTO;
import com.sale_clothes.nhom11.entity.FavoriteProduct;
import com.sale_clothes.nhom11.entity.FileData;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.repository.FavoriteProductRepository;
import com.sale_clothes.nhom11.service.BaseRedisService;
import com.sale_clothes.nhom11.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteProductServiceImpl implements IService<FavoriteProduct,Long> {
    @Autowired
    private FavoriteProductRepository favoriteProductRepository;
    //Add product Favorite list

    @Transactional
    public String addProductToFavoriteList(String username, int productId) {
        if(!favoriteProductRepository.existsByUserAndProduct(username,productId)) {
            //Create new favorite product object
            FavoriteProduct favoriteProduct = FavoriteProduct.builder()
                    .sanPham(SanPham.builder().product_id(productId).build())
                    .khachHang(KhachHang.builder().khUserName(username).build())
                    .build();

            //Save to repo
            favoriteProductRepository.save(favoriteProduct);
            return "Sản phẩm đã được thêm vào danh sách yêu thích!";
        } else {
            favoriteProductRepository.deleteByUsernameAndProduct(username,productId);
            return "Sản phẩm đã được xóa khỏi danh sách yêu thích!";
        }
    }

    //Get product favorite list by username
    public List<ProductResponseDTO> getFavoriteProducts(String username) {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();

        //Get favorite products by username in database
        List<FavoriteProduct> favoriteProducts = favoriteProductRepository.findAllByUsername(username);

        //Loop list favorite
        for(FavoriteProduct favoriteProduct : favoriteProducts) {
            //Set information for productResponse
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProductId(favoriteProduct.getSanPham().getProduct_id());
            productResponseDTO.setName(favoriteProduct.getSanPham().getName());
            productResponseDTO.setBasePrice(favoriteProduct.getSanPham().getBase_price());


            //loop list variant in favorite product to set variantDTO list
            List<VariantDTO> variantDTOS = favoriteProduct.getSanPham().getProductVariants().stream().map(variant -> {
                List<String> imageUrls = new ArrayList<>();
                VariantDTO variantDTO = new VariantDTO();

                for (FileData fileData : variant.getFileDataList()) {
                    imageUrls.add("http://localhost:8081/images/" + fileData.getName());
                }

                variantDTO.setVariant_id(variant.getVariant_id());
                variantDTO.setSize(variant.getSize());
                variantDTO.setColor_id(variant.getColor().getColorID());
                variantDTO.setColorCode(variant.getColor().getColorCode());
                variantDTO.setImageUrl(imageUrls);
                return variantDTO;
            }).collect(Collectors.toList());
            productResponseDTO.setVariants(variantDTOS);
            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }
    //Delete favorite product by username
    @Transactional
    public void deleteByUsernameAndProductId(String username, int productId) {
        favoriteProductRepository.deleteByUsernameAndProduct(username, productId);
    }
    @Override
    public FavoriteProduct create(FavoriteProduct dto) {
        return null;
    }

    @Override
    public List<FavoriteProduct> getAll() {
        List<FavoriteProduct> favoriteProducts = favoriteProductRepository.findAll();
        return favoriteProducts;
    }

    @Override
    public FavoriteProduct getById(Long id) {
        return null;
    }

    @Override
    public void update(Long id, FavoriteProduct dto) {

    }

    @Override
    public void delete(Long id) {
    }
}
