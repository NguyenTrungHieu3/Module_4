package com.example.validate_form_dang_ky.service;

import com.example.validate_form_dang_ky.entity.User;
import com.example.validate_form_dang_ky.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        this.userRepository.save(user);
    }
}
