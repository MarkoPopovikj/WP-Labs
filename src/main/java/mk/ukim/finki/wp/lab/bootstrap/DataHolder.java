package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void init() {
        // 1. Create 3 Authors first
        authors.add(new Author("Robert", "Greene", "USA", "Author of books on strategy, power, and seduction."));
        authors.add(new Author("Fyodor", "Dostoevsky", "Russia", "Novelist, philosopher, and journalist."));
        authors.add(new Author("J.K.", "Rowling", "UK", "Author of the Harry Potter fantasy series."));

        // 2. Create 10 Books and assign an Author to each
        // We use authors.get(i % 3) to cycle through the 3 authors
        books.add(new Book("The 48 Laws of Power", "Psychology", 4.5, authors.get(0)));
        books.add(new Book("Crime and Punishment", "Classic", 4.8, authors.get(1)));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "Fantasy", 4.7, authors.get(2)));

        books.add(new Book("The Art of Seduction", "Psychology", 4.3, authors.get(0)));
        books.add(new Book("The Brothers Karamazov", "Classic", 4.9, authors.get(1)));
        books.add(new Book("Harry Potter and the Chamber of Secrets", "Fantasy", 4.6, authors.get(2)));

        books.add(new Book("Mastery", "Self-Help", 4.4, authors.get(0)));
        books.add(new Book("The Idiot", "Classic", 4.5, authors.get(1)));
        books.add(new Book("Harry Potter and the Prisoner of Azkaban", "Fantasy", 4.8, authors.get(2)));

        books.add(new Book("The Laws of Human Nature", "Psychology", 4.6, authors.get(0)));

        // 3. Initialize reservations list
        reservations = new ArrayList<>();
    }
}
