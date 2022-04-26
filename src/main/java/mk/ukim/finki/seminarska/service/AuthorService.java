package mk.ukim.finki.seminarska.service;

import mk.ukim.finki.seminarska.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long originId);

    Optional<Author> edit(Long id, String name, String surname, Long originId);

    void deleteById(Long id);
}
