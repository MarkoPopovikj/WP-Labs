package mk.ukim.finki.wp.lab.web.controler;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String bookName,
                               @RequestParam(required = false) Double rating,
                               @RequestParam(required = false) Long authorId,
                               Model model){

        if(error != null){
            model.addAttribute("error", error);
        }

        List<Book> books = this.bookService.findAll(bookName, rating, authorId);

        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.findAll());

        return "listBooks";
    }

    @GetMapping("/book-form")
    public String getAddBookPage(Model model){

        model.addAttribute("authors", authorService.findAll());

        return "book-form";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id ,Model model){

        Book book = bookService.findBookById(id)
                .orElseThrow(IllegalArgumentException::new);

        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());

        return "book-form";
    }

    @PostMapping("/book-form")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){

        bookService.addBook(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/book-form/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){

        bookService.editBook(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){

        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
