package mk.ukim.finki.seminarska.web;

import mk.ukim.finki.seminarska.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.seminarska.service.UserService;
import mk.ukim.finki.seminarska.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user = this.userService.login(request.getParameter("username"),
                    request.getParameter("password"));
            model.addAttribute("user", user);
            return "redirect:/books";
        } catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("bodyContent", "login");

            return "master-template";
        }
    }
}
