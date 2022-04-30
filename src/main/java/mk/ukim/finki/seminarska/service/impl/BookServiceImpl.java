package mk.ukim.finki.seminarska.service.impl;

import mk.ukim.finki.seminarska.model.Author;
import mk.ukim.finki.seminarska.model.Book;
import mk.ukim.finki.seminarska.model.Details;
import mk.ukim.finki.seminarska.model.User;
import mk.ukim.finki.seminarska.model.exception.AuthorNotFoundException;
import mk.ukim.finki.seminarska.model.exception.BookNotFoundException;
import mk.ukim.finki.seminarska.model.exception.DetailsNotFoundException;
import mk.ukim.finki.seminarska.model.exception.UserNotFoundException;
import mk.ukim.finki.seminarska.repository.AuthorRepository;
import mk.ukim.finki.seminarska.repository.BookRepository;
import mk.ukim.finki.seminarska.repository.DetailsRepository;
import mk.ukim.finki.seminarska.repository.UserRepository;
import mk.ukim.finki.seminarska.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final DetailsRepository detailsRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           UserRepository userRepository,
                           DetailsRepository detailsRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.detailsRepository = detailsRepository;
    }

    @Override
    public List<Book> findALl() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String title, Long authorId, Long detailsId, Integer copies) {

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        Details details = this.detailsRepository.findById(detailsId)
                .orElseThrow(() -> new DetailsNotFoundException(detailsId));

        Book book = new Book(title, author, details, copies);

        book.getAuthor().getBooks().add(book);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String title, Long authorId, Long detailsId, Integer copies) {

        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        Details details = this.detailsRepository.findById(detailsId)
                .orElseThrow(() -> new DetailsNotFoundException(detailsId));

        book.setTitle(title);
        book.setDetails(details);
        book.setCopies(copies);

        if (!book.getAuthor().getId().equals(authorId)) {
            book.getAuthor().getBooks().remove(book);
            book.setAuthor(author);
            book.getAuthor().getBooks().add(book);
        }

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> rentABook(Long id, String username) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        book.getUsers().add(user);
        book.setCopies(book.getCopies() - 1);

        user.getBooks().add(book);
        this.userRepository.save(user);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> returnABook(Long id, String username) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        book.getUsers().remove(user);
        book.setCopies(book.getCopies() + 1);

        this.userRepository.save(user);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
