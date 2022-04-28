package mk.ukim.finki.seminarska.model;

import lombok.Data;
import mk.ukim.finki.seminarska.model.enumerations.Continent;

import javax.persistence.*;

@Data
@Entity
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    @Enumerated(value = EnumType.STRING)
    private Continent continent;

    public Origin() {
    }

    public Origin(String country, Continent continent) {
        this.country = country;
        this.continent = continent;
    }
}
