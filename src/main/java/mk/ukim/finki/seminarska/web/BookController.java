package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.Author;
import mk.ukim.finki.seminarska.model.Book;
import mk.ukim.finki.seminarska.model.Details;
import mk.ukim.finki.seminarska.model.exception.BookNotFoundException;
import mk.ukim.finki.seminarska.service.AuthorService;
import mk.ukim.finki.seminarska.service.BookService;
import mk.ukim.finki.seminarska.service.DetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final DetailsService detailsService;

    public BookController(BookService bookService, AuthorService authorService, DetailsService detailsService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.detailsService = detailsService;
    }

    @GetMapping
    public String showBooks(Model model) {
        List<Book> books = this.bookService.findALl();
        model.addAttribute("books", books);
        return "books.html";
    }

    @GetMapping("/add")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBook(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Details> details = this.detailsService.findAll();

        model.addAttribute("authors", authors);
        model.addAttribute("details", details);
        return "form-book.html";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Details> details = this.detailsService.findAll();
        Book book = this.bookService.findById(id).get();

        Author author = book.getAuthor();
        Details detail = book.getDetails();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("details", details);
        model.addAttribute("author", author);
        model.addAttribute("detail", detail);
        return "form-book.html";
    }

    @PostMapping("/add-book")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String title,
                           @RequestParam Long author,
                           @RequestParam Long details,
                           @RequestParam Integer copies) {


        if (id != null) {
            this.bookService.edit(id, title, author, details, copies);
        } else {
            this.bookService.save(title, author, details, copies);
        }


        return "redirect:/books";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/show-users/{id}")
    public String showUsers(@PathVariable Long id, Model model) {
        Book book = this.bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        model.addAttribute("book", book);
        return "";
    }

    @GetMapping("/{id}")
    public String rentABook(@PathVariable Long id,
                            @RequestParam String username) {
        this.bookService.rentABook(id, username);

        return "";
    }
}