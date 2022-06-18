package com.blogapp.blogapplication.services;

import com.blogapp.blogapplication.models.ImageEntity;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;


public interface ImageService {
  public ImageEntity storeImage(MultipartFile file) throws Exception;
  
  public Optional<ImageEntity> getFile(Long id);

}
