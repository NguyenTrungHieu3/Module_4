package com.example.ung_dung_muon_sach.service;

public interface IBorrowService {
    String borrowBook(Long bookId);
    void returnBook (String code);
}
