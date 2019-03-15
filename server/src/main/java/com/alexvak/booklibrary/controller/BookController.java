package com.alexvak.booklibrary.controller;

import com.alexvak.booklibrary.model.Book;
import com.alexvak.booklibrary.payload.BookRequest;
import com.alexvak.booklibrary.payload.BookResponse;
import com.alexvak.booklibrary.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/book/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BookResponse> saveBook(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("image") MultipartFile image,
                                                 @Valid BookRequest bookRequest) {

        Book book = bookService.storeBook(file, image, bookRequest);

        String bookDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/book/")
                .path(String.valueOf(book.getId()))
                .toUriString();

        return new ResponseEntity<>(new BookResponse(book.getTitle(), bookDownloadUri,
                file.getContentType(), file.getSize()), HttpStatus.CREATED);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Resource> getBook(@PathVariable String bookId) {

        Book book = bookService.getBook(Long.valueOf(bookId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(book.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + book.getTitle() + "\"")
                .body(new ByteArrayResource(book.getData()));
    }

}
