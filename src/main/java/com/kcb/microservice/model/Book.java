package com.kcb.microservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank(message = "Book title cannot be empty")
	private String title;
	
	@Column
	@NotBlank(message = "Author name cannot be empty")
	private String author;
	
	@Column(name = "YEAR_PUBLISHED")
	private Integer year;
}
