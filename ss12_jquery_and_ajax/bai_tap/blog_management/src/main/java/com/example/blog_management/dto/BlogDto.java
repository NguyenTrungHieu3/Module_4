package com.example.blog_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BlogDto {
    private Long id;
    private String title;
    private String content;
    private String summary;
    private String author;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;
    private Date createdAt;
    private Date updatedAt;
}
