package com.example.validate_form_dang_ky.controller;

import com.example.validate_form_dang_ky.dto.UserDto;
import com.example.validate_form_dang_ky.entity.User;
import com.example.validate_form_dang_ky.service.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "user/create";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "user/create";
        }

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        this.userService.add(user);
        redirectAttributes.addFlashAttribute("mess", "Add user successfully!");
        return "redirect:/user/result";
    }
}
