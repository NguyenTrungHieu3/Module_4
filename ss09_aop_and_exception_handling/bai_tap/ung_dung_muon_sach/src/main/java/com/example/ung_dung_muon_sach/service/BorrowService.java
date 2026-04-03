package com.example.ung_dung_muon_sach.service;

import com.example.ung_dung_muon_sach.entity.Book;
import com.example.ung_dung_muon_sach.entity.Borrow;
import com.example.ung_dung_muon_sach.entity.BorrowStatus;
import com.example.ung_dung_muon_sach.repository.IBookRepository;
import com.example.ung_dung_muon_sach.repository.IBorrowRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowService implements IBorrowService{
    private final IBorrowRepository borrowRepository;
    private final IBookRepository bookRepository;
    public BorrowService(IBorrowRepository borrowRepository, IBookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }
    @Override
    public String borrowBook(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Không tìm thấy sách"));
        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Sách đã hết");
        }

        book.setQuantity(book.getQuantity() - 1);
        this.bookRepository.save(book);

        String code = generateBorrowCode();
        Borrow borrow = new Borrow();
        borrow.setBorrowCode(code);
        borrow.setStatus(BorrowStatus.BORROWED);
        borrow.setBorrowDate(new Date());
        borrow.setBook(book);

        this.borrowRepository.save(borrow);
        return code;
    }

    private String generateBorrowCode() {
        return String.valueOf((int)(Math.random() * 90000) + 10000);
    }

    @Override
    public void returnBook(String code) {
        Borrow borrow = this.borrowRepository.findByBorrowCode(code).orElseThrow(() -> new RuntimeException("Mã mượn không tồn tại"));
        if (borrow.getStatus() == BorrowStatus.RETURNED) {
            throw new RuntimeException("Sách đã được trả trước đó");
        }

        Book book = borrow.getBook();

        book.setQuantity(book.getQuantity() + 1);
        this.bookRepository.save(book);

        borrow.setStatus(BorrowStatus.RETURNED);
        borrow.setReturnDate(new Date());
        this.borrowRepository.save(borrow);
    }
}
