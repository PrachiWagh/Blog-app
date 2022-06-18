package com.blogapp.blogapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.blogapp.blogapplication.models.UserEntity;
import com.blogapp.blogapplication.repositories.UserRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogapplicationApplicationTests {
  @Autowired
  UserRepository userRepository;

  // @Test
  // void findByEmailWorking() {

  //   String username = "prachi";
  //   UserEntity user = userRepository.findByUsername(username);
  //   assertEquals(user.getUsername(), username);
  // }

}
