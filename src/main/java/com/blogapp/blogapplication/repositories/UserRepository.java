package com.blogapp.blogapplication.repositories;

import com.blogapp.blogapplication.models.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String email);
  
  public UserEntity findByEmail(String email);

  public UserEntity findByUsername(String username);
}