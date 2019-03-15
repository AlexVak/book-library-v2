package com.alexvak.booklibrary.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BookRequest {

    @NotBlank
    @Size(min = 5, max = 100)
    private final String title;
    @Size(max = 255)
    private final String description;
}
