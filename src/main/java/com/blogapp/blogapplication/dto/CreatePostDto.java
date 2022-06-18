package com.blogapp.blogapplication.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreatePostDto {
  @NonNull
  private String title;
  
  @NonNull
  private String body;
  
  @NonNull
  private String username;
}
