package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll(String text, Double rating, Long authorId);

    Optional<Book> findBookById(long id);

    Book addBook(String title,
                 String genre,
                 Double averageRating,
                 Long authorId);

    Book editBook(Long bookId,
                  String title,
                  String genre,
                  Double averageRating,
                  Long authorId);

    void deleteBook(long id);
}
