package com.alexvak.booklibrary.exception;

public class BookNotFoundException extends RuntimeException {

    private static final String NOT_FOUND = "Book not found. ID: %s";

    public BookNotFoundException(Long id) {
        this(String.valueOf(id));
    }

    private BookNotFoundException(String id) {
        super(String.format(NOT_FOUND, id));
    }
}
