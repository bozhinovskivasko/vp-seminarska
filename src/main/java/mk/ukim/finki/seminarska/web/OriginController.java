package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.Origin;
import mk.ukim.finki.seminarska.model.enumerations.Continent;
import mk.ukim.finki.seminarska.service.OriginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/origin")
public class OriginController {

    private final OriginService originService;

    public OriginController(OriginService originService) {
        this.originService = originService;
    }

    @GetMapping("/add")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addOrigin(Model model) {
        List<Origin> origins = this.originService.findAll();

        model.addAttribute("origins", origins);
        return "form-origin.html";
    }

    @PostMapping("/add-origin")
    public String saveOrigin(@RequestParam String country,
                             @RequestParam Continent continent, Model model) {

        this.originService.save(country, continent);

        List<Origin> origins = this.originService.findAll();
        model.addAttribute("origins", origins);

        return "form-author.html";
    }
}
