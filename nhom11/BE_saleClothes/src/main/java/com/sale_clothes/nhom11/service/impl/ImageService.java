package com.sale_clothes.nhom11.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sale_clothes.nhom11.entity.FileData;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.repository.FileDataRepository;
import com.sale_clothes.nhom11.repository.SanPhamRepository;

@Service
public class ImageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    private final String FOLDER_PATH = "D:\\WorkSpace\\Project\\saleClothes\\Image\\";

    @Transactional
    public String uploadImageToFileSystem(MultipartFile file, Integer spMa) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .sanPham(SanPham.builder().spMa(spMa).build())
                .build());
        file.transferTo(new File(filePath));
        if (filePath != null) {
            return "file uploaded successfully: " + filePath;
        }
        return null;
    }

    public String uploadImagesToFileSystem(List<MultipartFile> files, Integer spMa) throws IOException {

        StringBuilder resultMessage = new StringBuilder();

        for (MultipartFile file : files) {
            // Tạo đường dẫn cho từng file
            String filePath = FOLDER_PATH + file.getOriginalFilename();

            // Lưu thông tin file vào database
            FileData fileData = fileDataRepository.save(FileData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .filePath(filePath)
                    .sanPham(SanPham.builder().spMa(spMa).build())
                    .build());

            // Lưu file vào hệ thống tệp
            file.transferTo(new File(filePath));

            // Thêm thông báo thành công cho từng file
            resultMessage
                    .append("File uploaded successfully: ")
                    .append(filePath)
                    .append("\n");
            // Trả về thông báo nếu có ít nhất một file được upload thành công

        }
        if (resultMessage.length() > 0) {
            return resultMessage.toString();
        }

        return "No files were uploaded.";
    }

    public void deleteByNameAndSpMa(List<String> names, Integer spMa) {

        if (sanPhamRepository.existsById(spMa)) {
            for (String name : names) {
                fileDataRepository.deleteByNameAndSpMa(name, spMa);
            }
        } else {
            throw new RuntimeException("spMa is not exist!!");
        }
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public List<FileData> getAllImagesBySpMa(Integer spMa) {
        List<FileData> fileDataList = fileDataRepository.findAllBySpMa(spMa);
        if (fileDataList.isEmpty()) {
            throw new NotFoundException("Image not exist!!");
        }
        return fileDataList;
    }

    public List<FileData> getAllImage() {
        List<FileData> fileDataList = fileDataRepository.findAll();
        return fileDataList;
    }

    @Transactional
    public void deleteImageBySpMa(Long imageId) {
        if (fileDataRepository.existsById(Math.toIntExact(imageId))) {
            fileDataRepository.deleteById(imageId);
        } else {
            throw new NotFoundException("Image not exist!!");
        }
    }
}
