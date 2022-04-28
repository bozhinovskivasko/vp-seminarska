package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.enumerations.MainGenre;
import mk.ukim.finki.seminarska.service.DetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/details")
public class DetailsController {

    private final DetailsService detailsService;

    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping("/add-details")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDetails() {
        return "";
    }

    @PostMapping("/add-details")
    public String saveDetails(@RequestParam MainGenre mainGenre,
                              @RequestParam String description) {

        this.detailsService.save(mainGenre, description);
        return "";
    }
}
