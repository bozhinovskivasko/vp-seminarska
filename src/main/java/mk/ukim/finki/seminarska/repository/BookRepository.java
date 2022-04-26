package mk.ukim.finki.seminarska.repository;

import mk.ukim.finki.seminarska.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
