package com.sale_clothes.nhom11.service.impl;


import com.sale_clothes.nhom11.entity.FileData;

import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.repository.FileDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {


    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "D:\\WorkSpace\\Project\\saleClothes\\Image\\";



    public String uploadImageToFileSystem(MultipartFile file, Integer spMa) throws IOException {
        String filePath=FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
                        .sanPham(SanPham.builder()
                                .spMa(spMa)
                                .build())
                .build());
        file.transferTo(new File(filePath));
        if(filePath != null) {
            return "file uploaded successfully: " + filePath;
        }
        return null;
    }
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


}
