package mk.ukim.finki.seminarska.service;

import mk.ukim.finki.seminarska.model.Author;
import mk.ukim.finki.seminarska.model.Book;
import mk.ukim.finki.seminarska.model.enumerations.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findALl();

    Optional<Book> findById(Long id);

    Optional<Book> save(String title, Long authorId, Genre genre, Integer copies);

    Optional<Book> edit(Long id, String title, Long authorId, Genre genre, Integer copies);

    Optional<Book> rentABook(Long id, String username);

    void deleteById(Long id);
}

