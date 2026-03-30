package com.example.validate_form_dang_ky.service;

import com.example.validate_form_dang_ky.dto.UserDto;
import com.example.validate_form_dang_ky.entity.User;
import com.example.validate_form_dang_ky.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    void add(User user);
}
