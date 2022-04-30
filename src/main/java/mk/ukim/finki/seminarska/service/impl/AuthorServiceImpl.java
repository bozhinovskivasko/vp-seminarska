package mk.ukim.finki.seminarska.service.impl;

import mk.ukim.finki.seminarska.model.Author;
import mk.ukim.finki.seminarska.model.Origin;
import mk.ukim.finki.seminarska.model.exception.AuthorNotFoundException;
import mk.ukim.finki.seminarska.model.exception.OriginNotFoundException;
import mk.ukim.finki.seminarska.repository.AuthorRepository;
import mk.ukim.finki.seminarska.repository.OriginRepository;
import mk.ukim.finki.seminarska.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final OriginRepository originRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, OriginRepository originRepository) {
        this.authorRepository = authorRepository;
        this.originRepository = originRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long originId) {

        Origin origin = this.originRepository.findById(originId)
                .orElseThrow(() -> new OriginNotFoundException(originId));

        Author author = new Author(name, surname, origin);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long originId) {

        Origin origin = this.originRepository.findById(originId)
                .orElseThrow(() -> new OriginNotFoundException(originId));

        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setSurname(surname);
        author.setOrigin(origin);


        return Optional.of(this.authorRepository.save(author));
    }
}
