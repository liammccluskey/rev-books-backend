package com.example.backend.repositories;

import com.example.backend.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBooksByAuthorOrderByIdDesc(String author);
    List<Book> getBooksByGenreOrderByIdDesc(String genre);
    List<Book> getBooksByAuthorAndGenreOrderByIdDesc(String author, String genre);
}
