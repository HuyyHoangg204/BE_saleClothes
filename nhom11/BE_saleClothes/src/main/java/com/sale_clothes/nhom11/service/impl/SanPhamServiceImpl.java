package com.sale_clothes.nhom11.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.sale_clothes.nhom11.dto.VariantDTO;
import com.sale_clothes.nhom11.dto.response.ProductDetailResponseDTO;
import com.sale_clothes.nhom11.dto.response.ProductResponseDTO;
import com.sale_clothes.nhom11.entity.FileData;
import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.entity.DanhMucCon;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.mapper.SanPhamMapper;
import com.sale_clothes.nhom11.repository.DanhMucConRepository;
import com.sale_clothes.nhom11.repository.SanPhamRepository;
import com.sale_clothes.nhom11.service.SanPhamService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private DanhMucConRepository danhMucConRepository;
    @Autowired
    private SanPhamMapper sanPhamMapper;

    @Override
    @Transactional
    public SanPhamDTO createSanPhamDTO(SanPhamDTO sanPhamDTO) {


        SanPham sanPham = sanPhamMapper.mapToSanPham(sanPhamDTO);
        SanPham savedSanPham = sanPhamRepository.save(sanPham);
        return sanPhamMapper.mapToSanPhamDTO(savedSanPham);

    }

    @Override
    public List<SanPhamDTO> getAllSanPhamDTOs() {
        List<SanPham> sanPhams = sanPhamRepository.findAll();
        ArrayList<SanPhamDTO> sanPhamDTOS = new ArrayList<SanPhamDTO>();
        for (SanPham sanPham : sanPhams) {

            sanPhamDTOS.add(sanPhamMapper.mapToSanPhamDTO(sanPham));
        }
        return sanPhamDTOS;
    }



    @Override
    public SanPhamDTO findSanPhamDTOById(Integer id) {
        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);

        if (optionalSanPham.isPresent()) {
            return sanPhamMapper.mapToSanPhamDTO(optionalSanPham.get());
        } else {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
    }


    @Override
    public void updateSanPhamDTO(Integer id, SanPhamDTO sanPhamDTO) {
        SanPham sanPham2 = sanPhamMapper.mapToSanPham(sanPhamDTO);
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);

        if (sanPham.isPresent()) {
            SanPham sanPham1 = sanPham.get();
            sanPham1.setName(sanPham2.getName());
            sanPham1.setInstruction(sanPham2.getInstruction());
            sanPham1.setMaterial(sanPham2.getMaterial());
            sanPham1.setDescription(sanPham2.getDescription());
            sanPham1.setBase_price(sanPham2.getBase_price());
            sanPham1.setDiscount_percentage(sanPham2.getDiscount_percentage());
            sanPham1.setDmcMa(sanPham2.getDmcMa());

            // Kiểm tra xem dmcMa có tồn tại trong DB không
            if (sanPham2.getDmcMa() != null) {
                Optional<DanhMucCon> danhMucCon = danhMucConRepository.findById(sanPham2.getDmcMa().getDmcMa());
                if (danhMucCon.isPresent()) {
                    sanPham1.setDmcMa(danhMucCon.get());
                } else {
                    // Nếu không tìm thấy danh mục con, có thể ném lỗi hoặc xử lý theo yêu cầu
                    throw new RuntimeException("Danh mục con không tồn tại trong cơ sở dữ liệu");
                }
            }

            sanPhamRepository.save(sanPham1);
        } else {
            // Nếu sản phẩm không tồn tại trong DB, xử lý lỗi hoặc trả về thông báo thích hợp
            throw new RuntimeException("Sản phẩm không tồn tại trong cơ sở dữ liệu");
        }
    }


    @Override
    @Transactional
    public void deleteSanPhamDTOById(int id) {
        sanPhamRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> getSanPhamToShowManager() {
        List<Map<String, Object>> listProduct = new ArrayList<>();
        List<SanPham> listSanPham = sanPhamRepository.findAll();
        for(SanPham sanPham : listSanPham) {
            Map<String, Object> responese = new HashMap<>();
            responese.put("product_id",sanPham.getProduct_id());
            responese.put("name", sanPham.getName());
            responese.put("base_price",sanPham.getBase_price());
            responese.put("dmcMaId", sanPham.getDmcMa().getDmcMa());
            responese.put("discount_percentage", sanPham.getDiscount_percentage());
            listProduct.add(responese);
        }
        return listProduct;
    }

    //Get 12 product to show in homepage
    public List<ProductResponseDTO> getProductDetails() {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, 12);
        Page<SanPham> page = sanPhamRepository.findAll(pageable);

        List<SanPham> products = sanPhamRepository.findProductsWithVariants(page.getContent());


//        List<ProductVariant> variants = productVariantRepository.findVariantsWithFileData(
//                products.stream().flatMap(p -> p.getProductVariants().stream()).collect(Collectors.toList())
//        );

        for(SanPham sanPham : page.getContent()) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();

            double oldPrice = sanPham.getBase_price() / (1 - ((double) sanPham.getDiscount_percentage() / 100));

            List<VariantDTO> variantDTOS = sanPham.getProductVariants().stream().map(variant -> {
                List<String> imageUrls = new ArrayList<>();
                VariantDTO variantDTO = new VariantDTO();

                for(FileData fileData : variant.getFileDataList()) {
                    imageUrls.add("http://localhost:8081/images/" + fileData.getName());
                }


                variantDTO.setVariant_id(variant.getVariant_id());
                variantDTO.setSize(variant.getSize());
                variantDTO.setColor_id(variant.getColor().getColorID());
                variantDTO.setColorCode(variant.getColor().getColorCode());
                variantDTO.setImageUrl(imageUrls);
                return variantDTO;

            }).collect(Collectors.toList());

            productResponseDTO.setProductId(sanPham.getProduct_id());
            productResponseDTO.setBasePrice(sanPham.getBase_price());
            productResponseDTO.setName(sanPham.getName());
            productResponseDTO.setOldPrice(oldPrice);
            productResponseDTO.setVariants(variantDTOS);

            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }
    //Get 12 product to show best seller
    public List<ProductResponseDTO> getProductBestSeller() {
        int[] idProductList = {48,49,50,51,52,53,54,55,56,57,58,59};
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        List<SanPham> sanPhamList = new ArrayList<>();

        for (int id : idProductList) {
            sanPhamRepository.findById(id).ifPresent(sanPhamList::add);
        }

//        List<ProductVariant> variants = productVariantRepository.findVariantsWithFileData(
//                sanPhamList.stream()
//                        .flatMap(p -> p.getProductVariants().stream()) // Sửa lỗi flatMap
//                        .collect(Collectors.toList()) // Thu thập danh sách
//        );
        for(SanPham sanPham : sanPhamList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();

            double oldPrice = sanPham.getBase_price() / (1 - ((double) sanPham.getDiscount_percentage() / 100));

            List<VariantDTO> variantDTOS = sanPham.getProductVariants().stream().map(variant -> {
                List<String> imageUrls = new ArrayList<>();
                VariantDTO variantDTO = new VariantDTO();

                for(FileData fileData : variant.getFileDataList()) {
                    imageUrls.add("http://localhost:8081/images/" + fileData.getName());
                }


                variantDTO.setVariant_id(variant.getVariant_id());
                variantDTO.setSize(variant.getSize());
                variantDTO.setColor_id(variant.getColor().getColorID());
                variantDTO.setColorCode(variant.getColor().getColorCode());
                variantDTO.setImageUrl(imageUrls);
                return variantDTO;

            }).collect(Collectors.toList());

            productResponseDTO.setProductId(sanPham.getProduct_id());
            productResponseDTO.setBasePrice(sanPham.getBase_price());
            productResponseDTO.setName(sanPham.getName());
            productResponseDTO.setOldPrice(oldPrice);
            productResponseDTO.setVariants(variantDTOS);

            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }
    public List<ProductResponseDTO> getProductFlashSale() {
        int[] idProductList = {48,49,50,51,52,53,54,55,56,57,58,59};
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        List<SanPham> sanPhamList = new ArrayList<>();

        for (int id : idProductList) {
            sanPhamRepository.findById(id).ifPresent(sanPhamList::add);
        }

//        List<ProductVariant> variants = productVariantRepository.findVariantsWithFileData(
//                sanPhamList.stream()
//                        .flatMap(p -> p.getProductVariants().stream()) // Sửa lỗi flatMap
//                        .collect(Collectors.toList()) // Thu thập danh sách
//        );
        for(SanPham sanPham : sanPhamList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();

            double oldPrice = sanPham.getBase_price() / (1 - ((double) sanPham.getDiscount_percentage() / 100));

            List<VariantDTO> variantDTOS = sanPham.getProductVariants().stream().map(variant -> {
                List<String> imageUrls = new ArrayList<>();
                VariantDTO variantDTO = new VariantDTO();

                for(FileData fileData : variant.getFileDataList()) {
                    imageUrls.add("http://localhost:8081/images/" + fileData.getName());
                }


                variantDTO.setVariant_id(variant.getVariant_id());
                variantDTO.setSize(variant.getSize());
                variantDTO.setColor_id(variant.getColor().getColorID());
                variantDTO.setColorCode(variant.getColor().getColorCode());
                variantDTO.setImageUrl(imageUrls);
                return variantDTO;

            }).collect(Collectors.toList());

            productResponseDTO.setProductId(sanPham.getProduct_id());
            productResponseDTO.setBasePrice(sanPham.getBase_price());
            productResponseDTO.setName(sanPham.getName());
            productResponseDTO.setOldPrice(oldPrice);
            productResponseDTO.setVariants(variantDTOS);

            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }

    public List<ProductResponseDTO> getProductRecommend() {
        int[] idProductList = {48,49,50,51,52,53,54,55,56,57,58,59};
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        List<SanPham> sanPhamList = new ArrayList<>();

        for (int id : idProductList) {
            sanPhamRepository.findById(id).ifPresent(sanPhamList::add);
        }

//        List<ProductVariant> variants = productVariantRepository.findVariantsWithFileData(
//                sanPhamList.stream()
//                        .flatMap(p -> p.getProductVariants().stream()) // Sửa lỗi flatMap
//                        .collect(Collectors.toList()) // Thu thập danh sách
//        );
        for(SanPham sanPham : sanPhamList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();

            double oldPrice = sanPham.getBase_price() / (1 - ((double) sanPham.getDiscount_percentage() / 100));

            List<VariantDTO> variantDTOS = sanPham.getProductVariants().stream().map(variant -> {
                List<String> imageUrls = new ArrayList<>();
                VariantDTO variantDTO = new VariantDTO();

                for(FileData fileData : variant.getFileDataList()) {
                    imageUrls.add("http://localhost:8081/images/" + fileData.getName());
                }


                variantDTO.setVariant_id(variant.getVariant_id());
                variantDTO.setSize(variant.getSize());
                variantDTO.setColor_id(variant.getColor().getColorID());
                variantDTO.setColorCode(variant.getColor().getColorCode());
                variantDTO.setImageUrl(imageUrls);
                return variantDTO;

            }).collect(Collectors.toList());

            productResponseDTO.setProductId(sanPham.getProduct_id());
            productResponseDTO.setBasePrice(sanPham.getBase_price());
            productResponseDTO.setName(sanPham.getName());
            productResponseDTO.setOldPrice(oldPrice);
            productResponseDTO.setVariants(variantDTOS);

            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }

    //Get product detail
    public ProductDetailResponseDTO getProductDetail(int idProduct) {
        ProductDetailResponseDTO productDetailResponseDTO = new ProductDetailResponseDTO();

        Optional<SanPham> sanPham = sanPhamRepository.findById(idProduct);

        if(sanPham.isPresent()) {
            productDetailResponseDTO.setProduct_id(idProduct);
            productDetailResponseDTO.setInstruction(sanPham.get().getInstruction());
            productDetailResponseDTO.setMaterial(sanPham.get().getMaterial());
            productDetailResponseDTO.setProduct_code(sanPham.get().getProduct_code());
            productDetailResponseDTO.setDescription(sanPham.get().getDescription());
            productDetailResponseDTO.setBase_price(sanPham.get().getBase_price());
            productDetailResponseDTO.setName(sanPham.get().getName());
            productDetailResponseDTO.setDiscount_percentage(sanPham.get().getDiscount_percentage());

            DanhMucCon danhMucCon = danhMucConRepository.findById(sanPham.get().getDmcMa().getDmcMa()).get();
            String gender = danhMucCon.getDmMa().getDmType();
            productDetailResponseDTO.setGender(gender);

            double oldPrice = sanPham.get().getBase_price() / (1 - ((double) sanPham.get().getDiscount_percentage() / 100));
            productDetailResponseDTO.setOldPrice(oldPrice);

            List<VariantDTO> variantDTOS = sanPham.get().getProductVariants().stream().map(variant -> {
                List<String> imageUrls = new ArrayList<>();
                VariantDTO variantDTO = new VariantDTO();

                for(FileData fileData : variant.getFileDataList()) {
                    imageUrls.add("http://localhost:8081/images/" + fileData.getName());
                }

                variantDTO.setVariant_id(variant.getVariant_id());
                variantDTO.setSize(variant.getSize());
                variantDTO.setColor_id(variant.getColor().getColorID());
                variantDTO.setColorCode(variant.getColor().getColorCode());
                variantDTO.setImageUrl(imageUrls);
                return variantDTO;
            }).toList();
            productDetailResponseDTO.setVariants(variantDTOS);

        } else throw new NotFoundException("Product id not found");
        return productDetailResponseDTO;
    }

    //Get list product by list id
    public List<ProductResponseDTO> getProductListByListId(List<Integer> idList) {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        List<SanPham> sanPhamList = new ArrayList<>();

        for(int id : idList) {
            sanPhamRepository.findById(id).ifPresent(sanPhamList::add);
        }

        for(SanPham sanPham : sanPhamList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();

            productResponseDTO.setProductId(sanPham.getProduct_id());
            productResponseDTO.setName(sanPham.getName());
            productResponseDTO.setBasePrice(sanPham.getBase_price());

            double oldPrice = sanPham.getBase_price() / (1 - ((double) sanPham.getDiscount_percentage() / 100));
            productResponseDTO.setOldPrice(oldPrice);

            List<VariantDTO> variantDTOS = sanPham.getProductVariants().stream().map(variant -> {
                List<String> imageUrls = new ArrayList<>();
                VariantDTO variantDTO = new VariantDTO();

                for(FileData fileData : variant.getFileDataList()) {
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
}
