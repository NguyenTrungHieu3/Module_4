package com.example.blog_application.service;

import com.example.blog_application.entity.Post;
import com.example.blog_application.repository.IPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
    private final IPostRepository postRepository;

    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> search(String keyword, Pageable pageable) {
        return postRepository.findByTitleContaining(keyword, pageable);
    }

    @Override
    public Page<Post> findByCategory(Long categoryId, Pageable pageable) {
        return postRepository.findByCategory_Id(categoryId, pageable);
    }
}
