package com.kcb.microservice.service;

import com.kcb.microservice.model.Book;
import com.kcb.microservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	
	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}
	
	@Override
	public Book getBookDetailsById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
	}
	
	@Override
	public Book updateBook(Long id, Book book) {
		Book bookToUpdate = bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
		
		bookToUpdate.setTitle(book.getTitle());
		bookToUpdate.setAuthor(book.getAuthor());
		bookToUpdate.setYear(book.getYear());
		
		return bookRepository.save(bookToUpdate);
	}
	
	@Override
	public String deleteBook(Long id) {
		if (!bookRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ID: " + id);
		}
		bookRepository.deleteById(id);
		
		return "Book with ID " + id + " has been successfully deleted.";
	}
}
