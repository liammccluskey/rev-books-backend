package com.example.backend.repositories;

import com.example.backend.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT b FROM Book b
        WHERE (:author IS NULL OR b.author = :author)
        AND (:genre IS NULL OR b.genre = :genre)
        ORDER BY b.id
    """)
    List<Book> getBooks(String author, String genre);
}
