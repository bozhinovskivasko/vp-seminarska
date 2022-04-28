package mk.ukim.finki.seminarska.repository;

import mk.ukim.finki.seminarska.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
}
