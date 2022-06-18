package com.blogapp.blogapplication.services;

import com.blogapp.blogapplication.dto.CreatePostDto;
import com.blogapp.blogapplication.dto.PostResponseDto;
import com.blogapp.blogapplication.models.PostEntity;
import com.blogapp.blogapplication.models.UserEntity;
import com.blogapp.blogapplication.repositories.PostRepository;
import com.blogapp.blogapplication.repositories.UserRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public Collection<PostResponseDto> findAll() {
    return postRepository.findAllByOrderByCreationDateDesc();
  }
  
  @Override
  public PostEntity save(CreatePostDto createPostDto) {
    UserEntity user = userRepository.findByUsername(createPostDto.getUsername());
    return postRepository.save(new PostEntity(
        createPostDto.getTitle(),createPostDto.getBody(),user));
  }
  
  @Override
  public void deleteById(String id) {
    postRepository.deleteById(id);
  }
  
  @Override 
  public Collection<PostResponseDto> findPostsByUsername(String username) {
    return postRepository.findByCreatedBy(username);
  }
  
  @Override
  public Collection<PostResponseDto> searchPosts(String searchText) {
    return postRepository.searchPosts(searchText);
  }
}
