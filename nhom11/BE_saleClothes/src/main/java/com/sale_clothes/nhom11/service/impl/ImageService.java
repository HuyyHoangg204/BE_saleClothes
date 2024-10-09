package com.sale_clothes.nhom11.service.impl;


import com.sale_clothes.nhom11.entity.FileData;
import com.sale_clothes.nhom11.entity.Image;
import com.sale_clothes.nhom11.mapper.ImageMapper;
import com.sale_clothes.nhom11.repository.FileDataRepository;
import com.sale_clothes.nhom11.repository.ImageRepository;
import com.sale_clothes.nhom11.util.ImageUtils;
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
    private ImageRepository imageRepository;

    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "D:\\WorkSpace\\Project\\saleClothes\\Image\\";

    public String uploadImage(MultipartFile file) throws IOException {
      Image image = imageRepository.save(Image.builder()
                      .name(file.getOriginalFilename())
                      .type(file.getContentType())
                      .imageData(ImageUtils.compressImage(file.getBytes()))
              .build());
      if(image != null) {
          return "file uploaded successfully : " + file.getOriginalFilename();
      }
       return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
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
