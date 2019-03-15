package com.alexvak.booklibrary.service;

import com.alexvak.booklibrary.model.Book;
import com.alexvak.booklibrary.payload.BookRequest;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {

    Book storeBook(MultipartFile file, MultipartFile image, BookRequest bookRequest);

    Book getBook(Long bookId);
}
