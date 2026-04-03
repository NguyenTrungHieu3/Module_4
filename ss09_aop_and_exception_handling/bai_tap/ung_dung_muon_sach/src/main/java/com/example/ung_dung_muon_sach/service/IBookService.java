package com.example.ung_dung_muon_sach.service;

import com.example.ung_dung_muon_sach.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAllBooks();
    Book findBookById(Long id);
}
