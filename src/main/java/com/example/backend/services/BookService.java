package com.example.backend.services;

import com.example.backend.DTOs.BookDTO;
import com.example.backend.entities.Book;
import com.example.backend.exceptions.ApiException;
import com.example.backend.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks(String author, String genre) {
        if (!(author == null) && !(genre == null)) {
            return bookRepository.getBooksByAuthorAndGenreOrderByIdDesc(author, genre);
        } else if (!(author == null)) {
            return bookRepository.getBooksByAuthorOrderByIdDesc(author);
        } else if (!(genre == null)) {
            return bookRepository.getBooksByGenreOrderByIdDesc(genre);
        } else {
            return bookRepository.findAll();
        }
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        "Could not find a book with this id",
                        HttpStatus.NOT_FOUND
                ));
    }

    public Book createBook(BookDTO bookDTO) {
        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .genre(bookDTO.getGenre())
                .pages(bookDTO.getPages())
                .publishedYear(bookDTO.getPublishedYear())
                .build();

        return bookRepository.save(book);
    }
}
