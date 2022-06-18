package com.blogapp.blogapplication.controllers;

import com.blogapp.blogapplication.dto.CreatePostDto;
import com.blogapp.blogapplication.dto.PostResponseDto;
import com.blogapp.blogapplication.models.ImageEntity;
import com.blogapp.blogapplication.models.PostEntity;
import com.blogapp.blogapplication.services.ImageService;
import com.blogapp.blogapplication.services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(
  // Access-Control-Allow-Origin
  origins = { "*" },
  
  // Alternative to origins that supports more flexible originpatterns. 
  // Please, see CorsConfiguration.setAllowedOriginPatterns(List)for details.
  // originPatterns = { "" },   
  
  // Access-Control-Allow-Credentials
  allowCredentials = "false",
  
  // Access-Control-Allow-Headers
  allowedHeaders = { "*" },
  
  // Access-Control-Expose-Headers
  exposedHeaders = { "*" }
  
)
@RestController
@RequestMapping("/blogs")
public class PostController {
  @Autowired
  private PostService postService;
  
  @Autowired
  private ImageService imageService;

  @Autowired
  private ObjectMapper objectMapper;
  
  @PostMapping("/createpost")
  public ResponseEntity<?> createPost(@RequestBody CreatePostDto createPostDto) {
    try {
      postService.save(createPostDto);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(),
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Post created successfully!", HttpStatus.OK);
  }
  
  @CrossOrigin(origins = "http://localhost:8080/blogs")
  @GetMapping("/posts")
  public ResponseEntity<?> posts() {
    try {
      Collection<PostResponseDto> posts = postService.findAll();
      String returnString = objectMapper.writeValueAsString(posts);
      return new ResponseEntity<>(returnString, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
  }

  @GetMapping("/myposts")
  public ResponseEntity<?> myPosts(@RequestBody HashMap<String,String> username) {
    try {
      Collection<PostResponseDto> posts = postService.findPostsByUsername(username.get("username"));
      String returnString = objectMapper.writeValueAsString(posts);
      return new ResponseEntity<>(returnString, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchPosts(@RequestBody HashMap<String,String> searchText) {
    try {
      Collection<PostResponseDto> posts = postService.searchPosts(searchText.get("text"));
      String returnString = objectMapper.writeValueAsString(posts);
      return new ResponseEntity<>(returnString, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  @PostMapping("/createblog/uploadimage")
  public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
    try {
      ImageEntity image = imageService.storeImage(file);
      return new ResponseEntity<>(image.getId(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/posts/getimage")
  public ResponseEntity<?> getImage(HashMap<String,Long> imageId) {
    try {
      ImageEntity imageEntity = imageService.getFile(imageId.get("postid")).get();
      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(imageEntity.getFileType()))
          .header(HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + imageEntity.getFilename() + "\"")
               .body(new ByteArrayResource(imageEntity.getContent()));
          
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
