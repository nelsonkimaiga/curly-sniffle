package com.kcb.microservice;

import com.kcb.microservice.model.Book;
import com.kcb.microservice.repository.BookRepository;
import com.kcb.microservice.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookServiceImpl bookService;
	
	private Book book1;
	
	private Book book2;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		book1 = new Book(1L, "Book One", "Nelson Kimaiga", 1992);
		book2 = new Book(2L, "Art of The Deal", "Donald Trump", 2018);
	}
	
	@Test
	void createBook_shouldSaveAndReturnBook() {
		when(bookRepository.save(book1)).thenReturn(book1);
		
		Book savedBook = bookService.createBook(book1);
		
		assertNotNull(savedBook);
		assertEquals("Book One", savedBook.getTitle());
		verify(bookRepository, times(1)).save(book1);
	}
	
	@Test
	void findAllBooks_shouldReturnAllBooks() {
		when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
		
		List<Book> books = bookService.findAllBooks();
		
		assertEquals(2, books.size());
		verify(bookRepository, times(1)).findAll();
	}
	
	@Test
	void getBookDetailsById_whenBookExists_shouldReturnBook() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
		
		Book foundBook = bookService.getBookDetailsById(1L);
		
		assertEquals("Book One", foundBook.getTitle());
		verify(bookRepository, times(1)).findById(1L);
	}
	
	@Test
	void getBookDetailsById_whenBookDoesNotExist_shouldThrowException() {
		when(bookRepository.findById(99L)).thenReturn(Optional.empty());
		
		assertThrows(ResponseStatusException.class, () -> bookService.getBookDetailsById(99L));
	}
	
	@Test
	void updateBook_shouldUpdateAndReturnBook() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
		when(bookRepository.save(any(Book.class))).thenReturn(book1);
		
		book1.setTitle("Book One updated");
		Book updated = bookService.updateBook(1L, book1);
		
		assertEquals("Book One updated", updated.getTitle());
		verify(bookRepository, times(1)).save(book1);
	}
	
	@Test
	void deleteBook_shouldDeleteBookIfExists() {
		when(bookRepository.existsById(1L)).thenReturn(true);
		
		String response = bookService.deleteBook(1L);
		
		assertTrue(response.contains("successfully deleted"));
		verify(bookRepository, times(1)).deleteById(1L);
	}
	
	@Test
	void deleteBook_shouldThrowExceptionIfBookNotFound() {
		when(bookRepository.existsById(99L)).thenReturn(false);
		
		assertThrows(ResponseStatusException.class, () -> bookService.deleteBook(99L));
	}
}