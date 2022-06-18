package com.blogapp.blogapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
  @NonNull
  private String usernameOrEmail;
  @NonNull
  private String password;
    
}
