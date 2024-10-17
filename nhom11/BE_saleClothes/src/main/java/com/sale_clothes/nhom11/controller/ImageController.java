package com.sale_clothes.nhom11.controller;



import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.ImageDataResponse;
import com.sale_clothes.nhom11.entity.FileData;
import com.sale_clothes.nhom11.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController

public class ImageController {
    @Autowired
    private ImageService imageService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/image/fileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file,@RequestParam("spMa") Integer spMa) throws IOException {
        String uploadImage = imageService.uploadImageToFileSystem(file,spMa);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PostMapping("images/fileSystem")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> uploadImagesToFileSystem(@RequestParam("files") List<MultipartFile> files, @RequestParam("spMa") Integer spMa) throws IOException {
        String uploadImages = imageService.uploadImagesToFileSystem(files,spMa);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImages);
    }
    @GetMapping("/image/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=imageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/image/fileSystems/{spMa}")
    public ApiResponse<List<ImageDataResponse>> getAllImagesBySpMa(@PathVariable Integer spMa) {
        List<FileData> fileDataList = imageService.getAllImagesBySpMa(spMa);
        ArrayList<ImageDataResponse> imageDataResponses = new ArrayList<>();
        for(FileData fileData : fileDataList) {
            imageDataResponses.add(ImageDataResponse.builder()
                            .name(fileData.getName())
                            .imageUrl("http://localhost:8081/images/" + fileData.getName())
                            .type(fileData.getType())
                            .maSp(fileData.getSanPham().getSpMa())
                    .build());
        }
        return ApiResponse.<List<ImageDataResponse>>builder()
                .result(imageDataResponses)
                .build();
    }
    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws Exception {
        Path imagePath = Paths.get("D:/WorkSpace/Project/saleClothes/Image/").resolve(imageName);
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            throw new RuntimeException("Could not read the image file: " + imageName);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/image/fileSystems/{spMa}")
    public ApiResponse<?> deleteByNameAndSpMa(@PathVariable Integer spMa, @RequestBody Map<String, List<String>> payload) {
        List<String> names = payload.get("names");
        try {
            imageService.deleteByNameAndSpMa(names, spMa);
            return ApiResponse.builder()
                    .message("Delete image successfully")
                    .build();
        } catch (Exception ex) {
            return ApiResponse.builder()
                    .message("Delete image failed: " + ex.getMessage())
                    .build();
        }
    }

    @GetMapping("/images")
    public ApiResponse<List<ImageDataResponse>> getAllImage() throws Exception{
        List<FileData> fileDataList = imageService.getAllImage();
        ArrayList<ImageDataResponse> responseList = new ArrayList<ImageDataResponse>();
        for(FileData fileData : fileDataList) {
            responseList.add(ImageDataResponse.builder()
                            .maSp(fileData.getSanPham().getSpMa())
                            .name(fileData.getName())
                            .imageUrl("http://localhost:8081/images/" + fileData.getName())
                    .type(fileData.getType())
                    .build());
        }
        return ApiResponse.<List<ImageDataResponse>>builder()
                .result(responseList)
                .build();
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/image/fileSystem/{imageId}")
    public ApiResponse<?> deleteImage(@PathVariable Long imageId)  {
            imageService.deleteImageBySpMa(imageId);
            return ApiResponse.builder()
                    .message("Delete success image with id: " + imageId)
                    .build();
    }

}
