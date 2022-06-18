package com.blogapp.blogapplication.services;

import com.blogapp.blogapplication.dto.CreatePostDto;
import com.blogapp.blogapplication.dto.PostResponseDto;
import com.blogapp.blogapplication.models.PostEntity;

import java.util.Collection;

public interface PostService {

  Collection<PostResponseDto> findAll();
  
  PostEntity save(CreatePostDto createPostDto);
  
  void deleteById(String id);
  
  Collection<PostResponseDto> findPostsByUsername(String username);

  Collection<PostResponseDto> searchPosts(String searchText);
}
