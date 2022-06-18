package com.blogapp.blogapplication.dto;

import java.util.Date;
import java.util.List;


public interface PostResponseDto {
  Long getId();
   
  String getTitle();
  
  String getBody();
  
  Date getCreation_date();
  
  String getCreated_by();

  List<Long> getImages();
}
