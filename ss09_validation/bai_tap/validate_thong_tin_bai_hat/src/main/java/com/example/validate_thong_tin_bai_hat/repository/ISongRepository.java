package com.example.validate_thong_tin_bai_hat.repository;

import com.example.validate_thong_tin_bai_hat.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends JpaRepository<Song, Long> {
}
