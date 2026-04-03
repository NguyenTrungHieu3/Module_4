package com.example.ung_dung_muon_sach.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "borrow_code", unique = true)
    private String borrowCode;
    @Enumerated(EnumType.STRING)
    private BorrowStatus status;
    @Column(name = "borrow_date")
    Date borrowDate;
    @Column(name = "return_date")
    Date returnDate;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
