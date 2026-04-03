package com.example.ung_dung_muon_sach.repository;

import com.example.ung_dung_muon_sach.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBorrowRepository extends JpaRepository<Borrow, Long> {
    Optional<Borrow> findByBorrowCode(String code);
}
