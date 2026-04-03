package com.example.ung_dung_muon_sach.service;

import com.example.ung_dung_muon_sach.entity.Book;
import com.example.ung_dung_muon_sach.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{
    private final IBookRepository bookRepository;
    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> findAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sách!")) ;
    }
}
