package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.User;
import mk.ukim.finki.seminarska.model.exception.UserNotFoundException;
import mk.ukim.finki.seminarska.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String showMyBooks(HttpServletRequest request, Model model) {

        String username = request.getRemoteUser();
        User user = this.userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        model.addAttribute("user", user);
        model.addAttribute("bodyContent", "user-books.html");

        return "master-template.html";
    }
}
