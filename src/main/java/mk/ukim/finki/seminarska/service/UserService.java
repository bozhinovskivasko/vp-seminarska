package mk.ukim.finki.seminarska.service;

import mk.ukim.finki.seminarska.model.User;
import mk.ukim.finki.seminarska.model.enumerations.Role;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> register(String name, String surname, Integer age, String username, String password, String repeatPassword, Role role);

    User login(String username, String password);
}
