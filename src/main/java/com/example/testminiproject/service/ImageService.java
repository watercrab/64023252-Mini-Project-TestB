package com.example.testminiproject.service;



import com.example.testminiproject.models.ImageModel;
import com.example.testminiproject.repositories.ImageRepository;
import com.example.testminiproject.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String uploadMassageImage(MultipartFile file) throws IOException {
        ImageModel imageData = imageRepository.save(ImageModel.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return String.format("%s",imageData.getName());
        }
        return null;
    }
    public byte[] downloadMassageImage(Long id) {
        Optional<ImageModel> dbImageData = imageRepository.findById(id);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public void deleteByID(Long id){
        this.imageRepository.deleteById(id);
    }
}
