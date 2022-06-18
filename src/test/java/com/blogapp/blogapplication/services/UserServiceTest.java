package com.blogapp.blogapplication.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.blogapp.blogapplication.BlogapplicationApplication;
import com.blogapp.blogapplication.dto.UserLoginDto;
import com.blogapp.blogapplication.dto.UserRegistrationDto;
import com.blogapp.blogapplication.models.UserEntity;
import com.blogapp.blogapplication.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest(classes = {BlogapplicationApplication.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
public class UserServiceTest {
  @InjectMocks
  private UserServiceImpl userService;
  @MockBean
  private UserRepository userRepositoryMock;
  private ObjectMapper objectMapper;
  
  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  
    objectMapper = new ObjectMapper();
  }

  @Test
  void userRegistrationTestForAlreadyExistingUsername() throws Exception {
    when(userRepositoryMock.existsByUsername(any(String.class))).thenReturn(true);
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
        "prachi","psw@gmail.com","123");
    assertEquals(userService.existsByUsername(userRegistrationDto),true);
  }
  
  @Test
  void userRegistrationTestForAlreadyExistingEmail() throws Exception {
    when(userRepositoryMock.existsByUsername(any(String.class))).thenReturn(true);
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
        "prachi","psw@gmail.com","123");
    assertEquals(userService.existsByUsername(userRegistrationDto),true);
  }

  @Test
  void userRegistrationTestForFindByUsername() throws Exception {
    when(userRepositoryMock.existsByUsername(any(String.class))).thenReturn(true);
    when(userRepositoryMock.findByUsername("prachi")).thenReturn(
        new UserEntity("prachi","psw@gmail.com","123"));
    UserLoginDto userLoginDto = new UserLoginDto(
        "prachi","123");
    assertEquals(userService.findByUsername(userLoginDto).getUsername(),"prachi");
  }

}
