package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> getBookById(long id);
    Book saveBook(Book book);
    void deleteBook(long id);

    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
}
