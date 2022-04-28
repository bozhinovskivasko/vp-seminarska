package mk.ukim.finki.seminarska.service.impl;

import mk.ukim.finki.seminarska.model.Details;
import mk.ukim.finki.seminarska.model.enumerations.MainGenre;
import mk.ukim.finki.seminarska.repository.DetailsRepository;
import mk.ukim.finki.seminarska.service.DetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsServiceImpl implements DetailsService {

    private final DetailsRepository detailsRepository;

    public DetailsServiceImpl(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    @Override
    public List<Details> findAll() {
        return this.detailsRepository.findAll();
    }

    @Override
    public Optional<Details> findById(Long id) {
        return this.detailsRepository.findById(id);
    }

    @Override
    public Optional<Details> save(MainGenre mainGenre, String description) {
        Details details = new Details(mainGenre, description);
        return Optional.of(this.detailsRepository.save(details));
    }
}
