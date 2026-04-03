package com.example.validate_thong_tin_bai_hat.controller;

import com.example.validate_thong_tin_bai_hat.dto.SongDto;
import com.example.validate_thong_tin_bai_hat.entity.Song;
import com.example.validate_thong_tin_bai_hat.service.SongService;
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
@RequestMapping("/song")
public class SongController {
    private final SongService songService;
    public SongController(SongService songService) {
        this.songService = songService;
    }
    @GetMapping("/create")
    public String addSong(Model model) {
        model.addAttribute("songDto", new SongDto());
        return "song/create";
    }

    @GetMapping("/update")
    public String updateSong(Model model) {
        model.addAttribute("songDto", new SongDto());
        return "song/update";
    }

    @GetMapping("/result")
    public String resultSong(Model model) {
        return "result";
    }

    @PostMapping("/create")
    public String createSong(@Valid @ModelAttribute SongDto songDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "song/create";
        }

        Song song = new Song();
        BeanUtils.copyProperties(songDto, song);
        this.songService.addSong(song);
        redirectAttributes.addFlashAttribute("message", "Song created successfully!");
        return "redirect:/song/result";
    }

    @PostMapping("/update")
    public String updateSong(@Valid @ModelAttribute SongDto songDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "song/update";
        }

        Song song = new Song();
        BeanUtils.copyProperties(songDto, song);
        this.songService.updateSong(song);
        redirectAttributes.addFlashAttribute("message", "Song updated successfully!");
        return "redirect:/song/result";
    }
}
