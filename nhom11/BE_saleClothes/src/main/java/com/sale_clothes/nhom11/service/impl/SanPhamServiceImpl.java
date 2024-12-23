package com.sale_clothes.nhom11.service.impl;

import java.util.*;

import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
