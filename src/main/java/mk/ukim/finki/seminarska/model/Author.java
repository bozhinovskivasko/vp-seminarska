package mk.ukim.finki.seminarska.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String fullName;

    @ManyToOne
    private Origin origin;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String surname, Origin origin) {
        this.name = name;
        this.surname = surname;
        this.origin = origin;
        this.books = new ArrayList<>();
        this.fullName = name + " " + surname;
    }
}
