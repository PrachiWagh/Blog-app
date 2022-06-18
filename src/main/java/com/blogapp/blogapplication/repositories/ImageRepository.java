package com.blogapp.blogapplication.repositories;

import com.blogapp.blogapplication.models.ImageEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {

    Optional<ImageEntity> findByPostId(Long id);
    
}
