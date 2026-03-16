package com.example.chuong_trinh_luu_giu_cau_hinh_hom_thu_dien_tu.service;

import com.example.chuong_trinh_luu_giu_cau_hinh_hom_thu_dien_tu.entity.EmailSettings;

public interface ISettingsService {
    EmailSettings getSettings();

    void updateSettings(EmailSettings settings);
}
