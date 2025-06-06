package com.lavanya.bookstore.controller;


import com.lavanya.bookstore.dto.BookDto;
import com.lavanya.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeMessage()
    {
        return new ResponseEntity<>("Welcome to the Book Store", HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookDto> getBook(@PathVariable String bookId) {
        BookDto bookDto = bookService.getBook(bookId);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> bookDtoList = bookService.getAllBooks();
        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> createBooks(@RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.createBook(bookDto);
        return new ResponseEntity<>(bookDto1, HttpStatus.OK);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.updateBookName(bookDto);
        return new ResponseEntity<>(bookDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        bookService.deleteBookByBookId(bookId);
        return new ResponseEntity<>("Book Deleted Successfully with id: " + bookId, HttpStatus.OK);
    }

    // get book by ID
//    @GetMapping("/{bookId}")
//    public ResponseEntity<String> getBook(@PathVariable String bookId)
//    {
//        return new ResponseEntity<>("Book Found - " + bookId, HttpStatus.OK);
//    }

//    @GetMapping("/")
//    public ResponseEntity<List<String>> getAllBooks()
//    {
//        List<String> books = new ArrayList<>();
//        books.add("Alex Xu system design");
//        books.add("coding for dummies");
//
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<String> createBooks( String book)
//    {
//        return new ResponseEntity<>("Book Created" , HttpStatus.OK);
//    }
//    @PutMapping("/")
//    public ResponseEntity<String> updateBooks( String book)
//    {
//        return new ResponseEntity<>("Book Updated" , HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{bookId}")
//    public ResponseEntity<String> deleteBooks(@PathVariable String bookId)
//    {a
//        return new ResponseEntity<>("Book deleted - " + bookId, HttpStatus.OK);
//    }



}
