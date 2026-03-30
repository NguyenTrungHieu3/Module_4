package com.example.validate_thong_tin_bai_hat.controller;

import com.example.validate_thong_tin_bai_hat.dto.SongDto;
import com.example.validate_thong_tin_bai_hat.entity.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/song")
public class SongController {
    @GetMapping("/create")
    public String addSong(Model model) {
        model.addAttribute("songDto", new SongDto());
        return "song/create";
    }
}
