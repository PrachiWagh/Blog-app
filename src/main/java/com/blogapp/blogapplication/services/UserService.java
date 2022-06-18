package com.blogapp.blogapplication.services;

import com.blogapp.blogapplication.dto.UserLoginDto;

import com.blogapp.blogapplication.dto.UserRegistrationDto;

import com.blogapp.blogapplication.models.UserEntity;

public interface UserService {
  Boolean existsByUsername(UserRegistrationDto userRegistrationDto);

  Boolean existsByEmail(UserRegistrationDto userRegistrationDto);

  UserEntity findByUsername(UserLoginDto userLoginDto);

  UserEntity findByEmail(UserLoginDto userLoginDto);

  Boolean validatePassword(UserEntity user, UserLoginDto userLoginDto);

  UserEntity save(UserRegistrationDto userRegistrationDto);
}
