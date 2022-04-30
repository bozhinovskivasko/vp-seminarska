package mk.ukim.finki.seminarska.service.impl;

import mk.ukim.finki.seminarska.model.Origin;
import mk.ukim.finki.seminarska.model.enumerations.Continent;
import mk.ukim.finki.seminarska.model.exception.OriginNotFoundException;
import mk.ukim.finki.seminarska.repository.OriginRepository;
import mk.ukim.finki.seminarska.service.OriginService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OriginServiceImpl implements OriginService {

    private final OriginRepository originRepository;

    public OriginServiceImpl(OriginRepository originRepository) {
        this.originRepository = originRepository;
    }

    @Override
    public List<Origin> findAll() {
        return this.originRepository.findAll();
    }

    @Override
    public Optional<Origin> save(String country, Continent continent) {
        Origin origin = new Origin(country, continent);
        return Optional.of(this.originRepository.save(origin));
    }

    @Override
    public Optional<Origin> edit(Long id, String country, Continent continent) {
        Origin origin = this.originRepository.findById(id)
                .orElseThrow(() -> new OriginNotFoundException(id));

        origin.setCountry(country);
        origin.setContinent(continent);

        return Optional.of(origin);
    }

}
