package com.example.blog_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
