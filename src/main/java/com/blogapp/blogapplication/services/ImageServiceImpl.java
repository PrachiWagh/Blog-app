package com.blogapp.blogapplication.services;

import com.blogapp.blogapplication.models.ImageEntity;
import com.blogapp.blogapplication.repositories.ImageRepository;
import com.blogapp.blogapplication.services.ImageService;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageServiceImpl implements ImageService {
  @Autowired
  ImageRepository imageRepository;
  
  @Override
  public ImageEntity storeImage(MultipartFile file) throws Exception {
    // Normalize file name
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      // Check if the file's name contains invalid characters
      if (fileName.contains("..")) {
        throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
      }

      ImageEntity imageFile = new ImageEntity(fileName, file.getContentType(), file.getBytes());

      return imageRepository.save(imageFile);
    } catch (IOException ex) {
      throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
    }
  }

  @Override
  public Optional<ImageEntity> getFile(Long id) {
    return imageRepository.findByPostId(id);
  }
  
}
