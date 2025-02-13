package com.sale_clothes.nhom11.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.ImageDataResponse;
import com.sale_clothes.nhom11.entity.FileData;
import com.sale_clothes.nhom11.service.impl.ImageService;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    // Upload a image
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/image/fileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(
            @RequestParam("image") MultipartFile file, @RequestParam("spMa") Integer variant_id) throws IOException {
        String uploadImage = imageService.uploadImageToFileSystem(file, variant_id);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    // Upload multiple images
    @PostMapping("images/fileSystem")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> uploadImagesToFileSystem(
            @RequestParam("files") List<MultipartFile> files, @RequestParam("variant_id") Integer variant_id)
            throws IOException {
        String uploadImages = imageService.uploadImagesToFileSystem(files, variant_id);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImages);
    }

    @GetMapping("/image/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = imageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    // Get all images by variant_id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/image/fileSystems/{variant_id}")
    public ApiResponse<List<ImageDataResponse>> getAllImagesBySpMa(@PathVariable Integer variant_id) {
        List<FileData> fileDataList = imageService.getAllImagesByVariantId(variant_id);
        ArrayList<ImageDataResponse> imageDataResponses = new ArrayList<>();
        for (FileData fileData : fileDataList) {
            imageDataResponses.add(ImageDataResponse.builder()
                    .id(fileData.getId())
                    .name(fileData.getName())
                    .imageUrl("http://localhost:8081/images/" + fileData.getName())
                    .type(fileData.getType())
                    .variant_id(fileData.getProductVariant().getVariant_id())
                    .build());
        }
        return ApiResponse.<List<ImageDataResponse>>builder()
                .result(imageDataResponses)
                .build();
    }

    // Get image by imageName
    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws Exception {
        Path imagePath = Paths.get("F:/WorkSpace/Project/saleClothes/Image/").resolve(imageName);
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            // Lấy MIME type của file
            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) {
                contentType = "application/octet-stream"; // MIME mặc định nếu không xác định được
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType)) // Đặt Content-Type
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            throw new RuntimeException("Could not read the image file: " + imageName);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/image/fileSystems/{spMa}")
    public ApiResponse<?> deleteByNameAndSpMa(
            @PathVariable Integer spMa, @RequestBody Map<String, List<String>> payload) {
        List<String> names = payload.get("names");
        try {
            imageService.deleteByNameAndSpMa(names, spMa);
            return ApiResponse.builder().message("Delete image successfully").build();
        } catch (Exception ex) {
            return ApiResponse.builder()
                    .message("Delete image failed: " + ex.getMessage())
                    .build();
        }
    }

    //    @GetMapping("/images")
    //    public ApiResponse<List<ImageDataResponse>> getAllImage() throws Exception {
    //        List<FileData> fileDataList = imageService.getAllImage();
    //        ArrayList<ImageDataResponse> responseList = new ArrayList<ImageDataResponse>();
    //        for (FileData fileData : fileDataList) {
    //            responseList.add(ImageDataResponse.builder()
    //                    .maSp(fileData.getSanPham().getProduct_id())
    //                    .name(fileData.getName())
    //                    .imageUrl("http://51.79.167.161:8081/images/" + fileData.getName())
    //                    .type(fileData.getType())
    //                    .build());
    //        }
    //        return ApiResponse.<List<ImageDataResponse>>builder()
    //                .result(responseList)
    //                .build();
    //    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/image/fileSystem/{imageId}")
    public ApiResponse<?> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImageBySpMa(imageId);
        return ApiResponse.builder()
                .message("Delete success image with id: " + imageId)
                .build();
    }
}
