package com.klef.jfsd.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.exam.model.Book;
import com.klef.jfsd.exam.service.BookService;


@Controller
public class BookController 
{
   @Autowired
   private BookService bookService;

   @GetMapping("/")
   public String main() 
   {
       return "index";
   }
  
   @GetMapping("/viewallbooks")
   public ModelAndView viewBooks(Model model)
   {
       ModelAndView mv = new ModelAndView("viewallbooks");
       model.addAttribute("bookdata", bookService.displayAllBooks());
       return mv;
   }
  
   @GetMapping("/addbook")
   public String addBookPage()
   {
       return "addbook";
   }
  
   @PostMapping("/insertbook")
   public ModelAndView insertBook(@RequestParam("title") String title, 
                                  @RequestParam("author") String author, 
                                  @RequestParam("genre") String genre, 
                                  @RequestParam("price") double price, 
                                  @RequestParam("publishedYear") int publishedYear)
   {
       ModelAndView mv = new ModelAndView("addbook");
       Book book = new Book();
       book.setTitle(title);
       book.setAuthor(author);
       book.setGenre(genre);
       book.setPrice(price);
       book.setPublishedYear(publishedYear);
       
       String message = bookService.addBook(book);
       mv.addObject("message", message);
       return mv;
   }
  
   @GetMapping("/updatebook")
   public ModelAndView updateBookPage(@RequestParam("id") Long id)
   {
       ModelAndView mv = new ModelAndView("updatebook");
       Book book = bookService.displayBookById(id);
       mv.addObject("bookdata", book);
       return mv;
   }
  
   @PostMapping("/updatebook")
   public ModelAndView updateBook(@RequestParam("id") Long id, 
                                  @RequestParam("title") String title, 
                                  @RequestParam("author") String author, 
                                  @RequestParam("genre") String genre, 
                                  @RequestParam("price") double price, 
                                  @RequestParam("publishedYear") int publishedYear)
   {
       ModelAndView mv = new ModelAndView("updatebook");
       Book book = new Book();
       book.setId(id);
       book.setTitle(title);
       book.setAuthor(author);
       book.setGenre(genre);
       book.setPrice(price);
       book.setPublishedYear(publishedYear);
       
       String message = bookService.updateBook(book);
       mv.addObject("message", message);
       return mv;
   }
  
   @GetMapping("/viewbookdetails")
   public ModelAndView viewBookDetails(@RequestParam("id") Long id)
   {
       ModelAndView mv = new ModelAndView("viewbookdetails");
       Book book = bookService.displayBookById(id);
       mv.addObject("bookdata", book);
       return mv;
   }
  
   @GetMapping("/deletebook")
   public ModelAndView deleteBook(@RequestParam("id") Long id)
   {
       ModelAndView mv = new ModelAndView("viewallbooks");
       String message = bookService.deleteBookById(id);
       mv.addObject("message", message);
       mv.addObject("bookdata", bookService.displayAllBooks());
       return mv;
   }
}

