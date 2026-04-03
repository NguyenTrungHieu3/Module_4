package com.example.ung_dung_muon_sach.controller;

import com.example.ung_dung_muon_sach.service.IBookService;
import com.example.ung_dung_muon_sach.service.IBorrowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;
    private final IBorrowService borrowService;

    public BookController(IBookService bookService, IBorrowService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping("")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "list";
    }

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "detail";
    }

    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Model model) {
        String code = borrowService.borrowBook(id);
        model.addAttribute("message", "Mượn thành công!");
        model.addAttribute("code", code);
        return "result";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String code, Model model) {
        borrowService.returnBook(code);
        model.addAttribute("message", "Trả sách thành công!");
        return "result";
    }
}
