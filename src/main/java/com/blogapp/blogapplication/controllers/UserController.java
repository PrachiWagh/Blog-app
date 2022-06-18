package com.blogapp.blogapplication.controllers;

import com.blogapp.blogapplication.dto.UserLoginDto;
import com.blogapp.blogapplication.dto.UserRegistrationDto;
import com.blogapp.blogapplication.models.UserEntity;
import com.blogapp.blogapplication.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;
  
  @PostMapping("/register")
  public ResponseEntity<?> registerUserAccount(
      @RequestBody UserRegistrationDto userRegistrationDto) {
    //add check if username already exists in db

    if (userService.existsByUsername(userRegistrationDto)) {
      return new ResponseEntity<>("Username already taken!",HttpStatus.BAD_REQUEST);
    } else if (userService.existsByEmail(userRegistrationDto)) {
      return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
    }
    //add check if email already exists in db
    userService.save(userRegistrationDto);
    return new ResponseEntity<>("User Registered Sucessfully",HttpStatus.OK);
  }

  @GetMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) {
    UserEntity user = null;
    if (userService.findByUsername(userLoginDto) != null) {
      user = userService.findByUsername(userLoginDto);
    } else if (userService.findByEmail(userLoginDto) != null) {
      user = userService.findByEmail(userLoginDto);
    } else {
      return new ResponseEntity<>("Incorrect username or email", HttpStatus.BAD_REQUEST);
    }
    if (!userService.validatePassword(user, userLoginDto)) {
      return new ResponseEntity<>("Incorrect Credentials!", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
  }
  
}
