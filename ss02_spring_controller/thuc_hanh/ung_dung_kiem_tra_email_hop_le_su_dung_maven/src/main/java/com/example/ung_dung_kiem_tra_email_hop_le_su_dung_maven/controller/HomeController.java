package com.example.ung_dung_kiem_tra_email_hop_le_su_dung_maven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HomeController {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static Pattern pattern;

    public HomeController() {
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @PostMapping(value = "/validate")
    public String user(@RequestParam(name = "email") String email,
                       ModelMap modelMap) {
        boolean isValid = validate(email);
        if (!isValid) {
            modelMap.addAttribute("message", "Email is invalid");
            return "home";
        } else {
            modelMap.addAttribute("email", email);
            return "success";
        }
    }

    private boolean validate(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
