package mk.ukim.finki.seminarska.repository;

import mk.ukim.finki.seminarska.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
