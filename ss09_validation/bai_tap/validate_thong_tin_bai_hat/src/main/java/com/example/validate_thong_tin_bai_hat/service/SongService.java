package com.example.validate_thong_tin_bai_hat.service;

import com.example.validate_thong_tin_bai_hat.entity.Song;
import com.example.validate_thong_tin_bai_hat.repository.ISongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService implements ISongService{
    private final ISongRepository songRepository;
    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    @Override
    public void addSong(Song song) {
        this.songRepository.save(song);
    }

    @Override
    public void updateSong(Song song) {
        Song songDB = this.songRepository.findById(song.getId()).orElse(null);
        if(songDB != null) {
            songDB.setId(song.getId());
            songDB.setName(song.getName());
            songDB.setArtist(song.getArtist());
            songDB.setGenre(song.getGenre());
            this.songRepository.save(songDB);
        }
    }
}
