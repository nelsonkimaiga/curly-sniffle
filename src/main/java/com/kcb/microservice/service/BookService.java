package com.kcb.microservice.service;

import com.kcb.microservice.model.Book;

import java.util.List;

public interface BookService {
	
	Book createBook(Book book);
	
	List<Book> findAllBooks();
	
	Book getBookDetailsById(Long id);
	
	Book updateBook(Long id, Book book);
	
	String deleteBook(Long id);
}
