package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.enumerations.Continent;
import mk.ukim.finki.seminarska.service.OriginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/origin")
public class OriginController {

    private final OriginService originService;

    public OriginController(OriginService originService) {
        this.originService = originService;
    }

    @GetMapping("/add-origin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addOrigin() {
        return "";
    }

    @PostMapping("/add-origin")
    public String saveOrigin(@RequestParam String country,
                             @RequestParam Continent continent) {

        this.originService.save(country, continent);
        return "";
    }
}
