package mk.ukim.finki.seminarska.model;

import lombok.Data;
import mk.ukim.finki.seminarska.model.enumerations.MainGenre;

import javax.persistence.*;

@Data
@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(value = EnumType.STRING)
    MainGenre mainGenre;

    @Column(length = 4000)
    String description;

    public Details() {
    }

    public Details(MainGenre mainGenre, String description) {
        this.mainGenre = mainGenre;
        this.description = description;
    }
}
