package com.example.blog_application.controller;

import com.example.blog_application.entity.Post;
import com.example.blog_application.service.ICategoryService;
import com.example.blog_application.service.IPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final IPostService postService;
    private final ICategoryService categoryService;

    public PostController(IPostService postService, ICategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listPosts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        Page<Post> posts;

        if (keyword != null && !keyword.isEmpty()) {
            posts = postService.search(keyword, pageable);
        } else if (categoryId != null) {
            posts = postService.findByCategory(categoryId, pageable);
        } else {
            posts = postService.findAllPosts(pageable);
        }

        model.addAttribute("posts", posts);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);

        return "blog/post";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("isEdit", false);
        return "blog/create-post";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("isEdit", true);
        return "blog/create-post";
    }

    @PostMapping
    public String createPost(@ModelAttribute("post") Post post, RedirectAttributes redirectAttributes) {
        postService.savePost(post);
        redirectAttributes.addFlashAttribute("successMsg", "Tao bai viet thanh cong.");
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Post post = postService.getPost(id);
        if (post == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Khong tim thay bai viet.");
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "blog/view-post";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Post post = postService.getPost(id);
        if (post == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Khong tim thay bai viet.");
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        model.addAttribute("isEdit", true);
        model.addAttribute("pageTitle", "Cap nhat bai viet");
        return "blog/create-post";
    }

    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable Long id,
                             @ModelAttribute("post") Post formPost,
                             RedirectAttributes redirectAttributes) {
        Post post = postService.getPost(id);
        if (post == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Khong tim thay bai viet.");
            return "redirect:/posts";
        }

        post.setTitle(formPost.getTitle());
        post.setCategory(formPost.getCategory());
        post.setContent(formPost.getContent());
        post.setSummary(formPost.getSummary());

        postService.savePost(post);
        redirectAttributes.addFlashAttribute("successMsg", "Cap nhat bai viet thanh cong.");
        return "redirect:/posts";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Post post = postService.getPost(id);
        if (post == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Khong tim thay bai viet.");
            return "redirect:/posts";
        }
        postService.deletePost(id);
        redirectAttributes.addFlashAttribute("successMsg", "Xoa bai viet thanh cong.");
        return "redirect:/posts";
    }
}
