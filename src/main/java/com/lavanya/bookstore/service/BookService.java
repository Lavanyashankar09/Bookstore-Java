package com.lavanya.bookstore.service;
import com.lavanya.bookstore.dto.BookDto;
import java.util.List;



public interface BookService {

    public BookDto getBook(String bookId);

    public List<BookDto> getAllBooks();

    public BookDto createBook(BookDto bookDto);

    public BookDto updateBookName(BookDto bookDto);

    public void deleteBookByBookId(String bookId);
}
