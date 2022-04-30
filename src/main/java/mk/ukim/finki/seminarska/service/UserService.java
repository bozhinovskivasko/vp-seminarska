package mk.ukim.finki.seminarska.service;

import mk.ukim.finki.seminarska.model.User;
import mk.ukim.finki.seminarska.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Integer age, Role role);

    User login(String username, String password);

    Optional<User> findByUsername(String username);
}
