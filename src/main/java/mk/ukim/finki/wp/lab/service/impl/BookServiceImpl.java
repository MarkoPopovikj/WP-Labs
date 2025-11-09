package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> findBookById(long id) {

        return this.bookRepository.getBookById(id);
    }

    @Override
    public Book addBook(String title, String genre, Double averageRating, Long authorId) {
        if(title == null || title.isEmpty() ||
                genre == null || genre.isEmpty() ||
                averageRating == null || averageRating <= 0.0 ||
                authorId == null
        ){
            throw new IllegalArgumentException("Invalid parameters");
        }

        Author author = this.authorRepository.findById(authorId).get();
        return this.bookRepository.saveBook(new Book(title, genre, averageRating, author));
    }

    @Override
    public Book editBook(Long bookId, String title, String genre, Double averageRating, Long authorId) {
        if(bookId == null ||
                title == null || title.isEmpty() ||
                genre == null || genre.isEmpty() ||
                averageRating == null || averageRating <= 0.0 ||
                authorId == null){

            throw new IllegalArgumentException("Invalid parameters");
        }

        Book book = this.bookRepository.getBookById(bookId).get();
        Author author = this.authorRepository.findById(authorId).get();

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return this.bookRepository.saveBook(book);
    }

    @Override
    public void deleteBook(long id) {

        bookRepository.deleteBook(id);
    }

    @Override
    public List<Book> findAll() {

        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBook(String text, Double rating) {

        if(text == null || text.isEmpty() || rating == null || rating < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        return this.bookRepository.searchBooks(text, rating);
    }
}
