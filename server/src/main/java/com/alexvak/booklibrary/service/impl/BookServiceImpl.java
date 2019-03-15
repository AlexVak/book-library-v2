package com.alexvak.booklibrary.service.impl;

import com.alexvak.booklibrary.exception.BookNotFoundException;
import com.alexvak.booklibrary.exception.BookSaveException;
import com.alexvak.booklibrary.model.Book;
import com.alexvak.booklibrary.payload.BookRequest;
import com.alexvak.booklibrary.repository.BookRepository;
import com.alexvak.booklibrary.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book storeBook(MultipartFile file, MultipartFile image, BookRequest bookRequest) {

        try {
            Book book = new Book(bookRequest.getTitle(), bookRequest.getDescription(),
                    image.getBytes(), file.getContentType(), file.getBytes());
            return bookRepository.save(book);
        } catch (IOException e) {
            throw new BookSaveException(bookRequest.getTitle());
        }
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
