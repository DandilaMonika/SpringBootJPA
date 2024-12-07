package com.klef.jfsd.exam.service;

import java.util.List;

import com.klef.jfsd.exam.model.Book;

public interface BookService {
    String addBook(Book book);
    String updateBook(Book book);
    String deleteBookById(Long id);
    List<Book> displayAllBooks();
    Book displayBookById(Long id);
}
