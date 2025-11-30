package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findAllByAuthor_Id(Long authorId);
    List<Book> findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(String text, Double rating);
}
