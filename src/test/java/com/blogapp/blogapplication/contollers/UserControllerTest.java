package com.blogapp.blogapplication.contollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import com.blogapp.blogapplication.BlogapplicationApplication;
import com.blogapp.blogapplication.controllers.UserController;
import com.blogapp.blogapplication.dto.UserRegistrationDto;
import com.blogapp.blogapplication.models.UserEntity;
import com.blogapp.blogapplication.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;



@SpringBootTest(classes = {BlogapplicationApplication.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
public class UserControllerTest {
  private ObjectMapper objectMapper;

  private MockMvc mvc;
  
  @MockBean
  private UserServiceImpl userService;
  
  
  @InjectMocks
  private UserController userController;
  
  @BeforeEach
  public void setup() {
    objectMapper = new ObjectMapper();
  
    MockitoAnnotations.openMocks(this);
  
    mvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void registrationApiTest() throws Exception {
    String uri = "/user/register";
    HashMap<String,String> mp = new HashMap<>();
    mp.put("username","p");
    mp.put("email","b@gmail.com");
    mp.put("password","123");
    String inputJson = objectMapper.writeValueAsString(mp);
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON).content(inputJson))
        .andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
    assertEquals("User Registered Sucessfully", 
        mvcResult.getResponse().getContentAsString());
  }

  //   @Test
  //   public void loginApiTest() throws Exception {
  //     String uri = "/user/login";
  //     HashMap<String,String> mp = new HashMap<>();
  //     mp.put("usernameOrEmail","psw@gmail.com");
  //     mp.put("password","123");
  //     String inputJson = objectMapper.writeValueAsString(mp);
  //     MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
  //         .contentType(MediaType.APPLICATION_JSON)
  //         .content(inputJson)).andReturn();
  //     int status = mvcResult.getResponse().getStatus();
  //     assertEquals(200, status);

  //   }
}
