package com.example.chuong_trinh_luu_giu_cau_hinh_hom_thu_dien_tu.controller;

import com.example.chuong_trinh_luu_giu_cau_hinh_hom_thu_dien_tu.entity.EmailSettings;
import com.example.chuong_trinh_luu_giu_cau_hinh_hom_thu_dien_tu.service.ISettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/settings")
@Controller
public class SettingsController {
    @Autowired
    private ISettingsService emailSettingsService;

    @GetMapping("")
    public String showSettings(Model model) {

        model.addAttribute("settings", emailSettingsService.getSettings());

        model.addAttribute("languages", new String[]{"English","Vietnamese","Japanese","Chinese"});

        model.addAttribute("pageSizes",  new int[]{5,10,15,25,50,100});

        return "settings";
    }

    @PostMapping("/update")
    public String updateSettings(
            @ModelAttribute("settings") EmailSettings settings,
            Model model
    ) {

        emailSettingsService.updateSettings(settings);

        model.addAttribute("languages",
                new String[]{"English","Vietnamese","Japanese","Chinese"});

        model.addAttribute("pageSizes",
                new int[]{5,10,15,25,50,100});

        model.addAttribute("message","Update success!");

        return "settings";
    }
}
