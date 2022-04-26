package mk.ukim.finki.seminarska.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String continent;

    public Origin() {
    }

    public Origin(String country, String continent) {
        this.country = country;
        this.continent = continent;
    }
}
