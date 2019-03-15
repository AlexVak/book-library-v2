package com.alexvak.booklibrary.exception;

public class BookSaveException extends RuntimeException {

    private static final String MESSAGE = "Could not store book %s. Please try again!";

    public BookSaveException(String title) {
        super(String.format(MESSAGE, title));
    }

}
