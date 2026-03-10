package com.example.backend.DTOs;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String author;
    private String genre;
    private int pages;
    private int publishedYear;
}
