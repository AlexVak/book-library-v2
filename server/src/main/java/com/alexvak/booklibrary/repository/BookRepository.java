package com.alexvak.booklibrary.repository;

import com.alexvak.booklibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
