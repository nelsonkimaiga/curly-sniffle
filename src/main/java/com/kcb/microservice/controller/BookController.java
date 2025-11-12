package com.kcb.microservice.controller;

import com.kcb.microservice.model.Book;
import com.kcb.microservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@Operation(summary = "Creates a new book")
	@PostMapping
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(book));
	}
	
	@Operation(summary = "Fetches all books")
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.status(HttpStatus.FOUND).body(bookService.findAllBooks());
	}
	
	@Operation(summary = "Gets book details by Id")
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookDetailsById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(bookService.getBookDetailsById(id));
	}
	
	@Operation(summary = "Updates a  book")
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, book));
	}
	
	@Operation(summary = "Deletes a  book")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id));
	}

}
