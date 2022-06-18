package com.blogapp.blogapplication.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "images")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NonNull
  private String filename;
  
  @NonNull
  private String fileType;
  
  @NonNull
  @Lob
  private byte[] content;

  @ManyToOne
  @JoinColumn(name = "postId",referencedColumnName = "id")
  private PostEntity post;
}
