package com.blogapp.blogapplication.services;

import com.blogapp.blogapplication.dto.UserLoginDto;
import com.blogapp.blogapplication.dto.UserRegistrationDto;
import com.blogapp.blogapplication.models.UserEntity;
import com.blogapp.blogapplication.repositories.UserRepository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Boolean existsByUsername(UserRegistrationDto userRegistrationDto) {
    return userRepository.existsByUsername(userRegistrationDto.getUsername());
  }

  @Override
  public Boolean existsByEmail(UserRegistrationDto userRegistrationDto) {
    return userRepository.existsByEmail(userRegistrationDto.getEmail());
  }

  @Override
  public UserEntity findByUsername(UserLoginDto userLoginDto) {
    if (userRepository.existsByUsername(userLoginDto.getUsernameOrEmail())) {
      return userRepository.findByUsername(userLoginDto.getUsernameOrEmail());
    }
    return null;
  }

  @Override
  public UserEntity findByEmail(UserLoginDto userLoginDto) {
    if (userRepository.existsByEmail(userLoginDto.getUsernameOrEmail())) {
      return userRepository.findByEmail(userLoginDto.getUsernameOrEmail());
    }
    return null;
  }

  @Override
  public UserEntity save(UserRegistrationDto userRegistrationDto) {
    // TODO Auto-generated method stub
    //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserEntity newUser = new UserEntity(userRegistrationDto.getUsername(),
        userRegistrationDto.getEmail(),userRegistrationDto.getPassword());
    return userRepository.save(newUser);
  }

  @Override
  public Boolean validatePassword(UserEntity user, UserLoginDto userLoginDto) {
    return user.getPassword().equals(userLoginDto.getPassword());
  }
 
}
