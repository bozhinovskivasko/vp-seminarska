package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.Author;
import mk.ukim.finki.seminarska.model.Book;
import mk.ukim.finki.seminarska.model.Details;
import mk.ukim.finki.seminarska.model.Origin;
import mk.ukim.finki.seminarska.model.exception.AuthorNotFoundException;
import mk.ukim.finki.seminarska.service.AuthorService;
import mk.ukim.finki.seminarska.service.OriginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final OriginService originService;

    public AuthorController(AuthorService authorService, OriginService originService) {
        this.authorService = authorService;
        this.originService = originService;
    }

    @GetMapping
    public String showAuthors(Model model) {
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors.html";
    }

    @GetMapping("/{id}")
    public String showAuthorsBooks(@PathVariable Long id, Model model) {
        Author author = this.authorService.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        model.addAttribute("author", author);

        return "author-works.html";
    }

    @GetMapping("/add")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addAuthor(Model model) {
        List<Origin> origins = this.originService.findAll();
        model.addAttribute("origins", origins);
        return "form-author.html";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            List<Origin> origins = this.originService.findAll();

            model.addAttribute("author", author);
            model.addAttribute("origins", origins);

            return "form-author.html";
        }

        return "redirect:/authors";
    }

    @PostMapping("/add-author")
    public String saveAuthor(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam Long origin) {

        if (id != null) {
            this.authorService.edit(id, name, surname, origin);
        } else {
            this.authorService.save(name, surname, origin);
        }
        return "redirect:/authors";
    }
}
