package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.Author;
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
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
    private final OriginService originService;

    public AuthorController(AuthorService authorService, OriginService originService) {
        this.authorService = authorService;
        this.originService = originService;
    }

    @GetMapping("/{id}")
    public String showAuthorsBooks(@PathVariable Long id, Model model) {
        Author author = this.authorService.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        model.addAttribute("author", author);

        return "";
    }

    @GetMapping("/add-author")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addAuthor(Model model) {
        List<Origin> origins = this.originService.findAll();
        model.addAttribute("origins", origins);
        return "";
    }

    @PostMapping("/add-author")
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam Long originId) {
        this.authorService.save(name, surname, originId);
        return "";
    }
}
