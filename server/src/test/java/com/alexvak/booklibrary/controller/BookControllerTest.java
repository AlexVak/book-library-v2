package com.alexvak.booklibrary.controller;

import com.alexvak.booklibrary.model.Book;
import com.alexvak.booklibrary.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest {

    private MockMvc mvc;

    @Mock
    private BookService bookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        BookController bookController = new BookController(bookService);
        mvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(ControllerExceptionHandler.class).build();
    }

    @Test
    public void saveBook() throws Exception {
        Book book = new Book("Test Book", "Test Book Description",
                "image".getBytes(), "pdf", "file".getBytes());
        book.setId(1L);

        when(bookService.storeBook(any(), any(), any())).thenReturn(book);

        mvc.perform(multipart("/book/save")
                .file("file", "file".getBytes())
                .file("image", "image".getBytes())
                .param("title", "Test Book")
                .param("description", "Test Book Description"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'title':'Test Book','bookDownloadUri':'http://localhost/book/1','fileType': null,'size': 4}"));

        verify(bookService, times(1)).storeBook(any(), any(), any());
    }

    @Test
    public void getBook() throws Exception {
        Book book = new Book("Test Book", "Test Book Description",
                "image".getBytes(), "application/pdf", "file".getBytes());
        book.setId(1L);

        when(bookService.getBook(book.getId())).thenReturn(book);

        mvc.perform(get("/book/" + book.getId())).andExpect(status().isOk());

        verify(bookService, times(1)).getBook(book.getId());

    }
}
