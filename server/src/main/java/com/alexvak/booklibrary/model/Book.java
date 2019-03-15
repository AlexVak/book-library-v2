package com.alexvak.booklibrary.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 100)
    private String title;

    @Size(max = 255)
    private String description;

    @Lob
    private byte[] image;

    @Size(max = 50)
    private String fileType;

    @Lob
    private byte[] data;

    public Book() {
    }

    public Book(@NotBlank @Size(min = 5, max = 100) String title, @Size(max = 255) String description, byte[] image, @Size(max = 50) String fileType, byte[] data) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.fileType = fileType;
        this.data = data;
    }
}
