package com.klef.jfsd.exam.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.exam.model.Book;
import com.klef.jfsd.exam.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {
  
    @Autowired
    private BookRepository bookRepository;

    @Override
    public String addBook(Book book) {
        bookRepository.save(book);
        return "Book added successfully!";
    }

    @Override
    public String updateBook(Book book) {
        if (bookRepository.existsById(book.getId())) {
            bookRepository.save(book);
            return "Book updated successfully!";
        } else {
            return "Book not found!";
        }
    }

    @Override
    public String deleteBookById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Book deleted successfully!";
        } else {
            return "Book not found!";
        }
    }

    @Override
    public List<Book> displayAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book displayBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
