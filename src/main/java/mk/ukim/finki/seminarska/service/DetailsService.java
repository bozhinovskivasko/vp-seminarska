package mk.ukim.finki.seminarska.service;

import mk.ukim.finki.seminarska.model.Details;
import mk.ukim.finki.seminarska.model.enumerations.MainGenre;

import java.util.List;
import java.util.Optional;

public interface DetailsService {

    List<Details> findAll();

    Optional<Details> findById(Long id);

    Optional<Details> save(MainGenre mainGenre, String description);
}
