package mk.ukim.finki.seminarska.service;

import mk.ukim.finki.seminarska.model.Origin;

import java.util.List;
import java.util.Optional;

public interface OriginService {

    List<Origin> findAll();

    Optional<Origin> findById(Long id);

    Optional<Origin> save(String country, String continent);

    Optional<Origin> edit(Long id, String country, String continent);

    void deleteById(Long id);
}
