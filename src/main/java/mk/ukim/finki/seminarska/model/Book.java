package mk.ukim.finki.seminarska.model;

import lombok.Data;
import mk.ukim.finki.seminarska.model.enumerations.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Author author;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Integer copies;

    @ManyToMany
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "user_username"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<User> users;

    public Book() {
    }

    public Book(String title, Author author, Genre genre, Integer copies) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.copies = copies;
        this.users = new ArrayList<>();
    }
}
