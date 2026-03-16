package com.example.chuong_trinh_luu_giu_cau_hinh_hom_thu_dien_tu.service;

import com.example.chuong_trinh_luu_giu_cau_hinh_hom_thu_dien_tu.entity.EmailSettings;
import org.springframework.stereotype.Service;

@Service
public class SettingsService implements ISettingsService{
    private EmailSettings emailSettings = new EmailSettings("English", 25, false, "Thor\nKing, Asgard");

    @Override
    public EmailSettings getSettings() {
        return emailSettings;
    }

    @Override
    public void updateSettings(EmailSettings settings) {
        this.emailSettings = settings;
    }
}
