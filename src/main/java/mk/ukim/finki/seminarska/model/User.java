package mk.ukim.finki.seminarska.model;

import lombok.Data;
import mk.ukim.finki.seminarska.model.enumerations.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "library_users")
public class User {

    @Id
    private String username;

    private String name;

    private String surname;

    private Integer age;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "users")
    private List<Book> books;

    public User() {
    }

    public User(String name, String surname, Integer age, String username, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.role = role;
        this.books = new ArrayList<>();
    }
}
