package mk.ukim.finki.seminarska.repository;

import mk.ukim.finki.seminarska.model.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {
}
