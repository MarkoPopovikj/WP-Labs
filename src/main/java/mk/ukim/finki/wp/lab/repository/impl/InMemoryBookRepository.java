package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public Optional<Book> getBookById(long id) {
        return DataHolder.books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public Book saveBook(Book book) {
        deleteBook(book.getId());
        DataHolder.books.add(book);

        return book;
    }

    @Override
    public void deleteBook(long id) {
        DataHolder.books.removeIf(b -> b.getId().equals(id));
    }

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(b -> b.getTitle().contains(text) &&
                        b.getAverageRating() >= rating)
                .collect(Collectors.toList());
    }
}
