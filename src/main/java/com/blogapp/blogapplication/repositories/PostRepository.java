package com.blogapp.blogapplication.repositories;

import com.blogapp.blogapplication.dto.PostResponseDto;
import com.blogapp.blogapplication.models.PostEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  
  Optional<PostEntity> findById(Long id);
  
  @Query(value = "Select *"
      + " from posts" 
      + " order by creation_date desc;",
      nativeQuery = true)
  Collection<PostResponseDto> findAllByOrderByCreationDateDesc();
  
  List<PostEntity> findAll();
  
  @Query(value = "Select * from posts where created_by = (?1)",nativeQuery = true)
  Collection<PostResponseDto> findByCreatedBy(String createdBy);
  
  @Query(value = " Select * from posts"
      + " where match( title,body,created_by )"
      + " against((?1));",
      nativeQuery = true)
  Collection<PostResponseDto> searchPosts(String searchText);

  void deleteById(String id);
}
