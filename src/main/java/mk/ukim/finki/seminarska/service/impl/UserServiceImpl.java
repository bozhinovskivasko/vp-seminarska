package mk.ukim.finki.seminarska.service.impl;

import mk.ukim.finki.seminarska.model.User;
import mk.ukim.finki.seminarska.model.enumerations.Role;
import mk.ukim.finki.seminarska.model.exception.PasswordDoNotMatchException;
import mk.ukim.finki.seminarska.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.seminarska.repository.UserRepository;
import mk.ukim.finki.seminarska.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> register(String name, String surname, Integer age, String username,
                                   String password, String repeatPassword, Role role) {

        if (!password.equals(repeatPassword))
            throw new PasswordDoNotMatchException();

        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        String encryptedPassword = this.passwordEncoder.encode(password);

        User user = new User(name, surname, age, encryptedPassword, password, role);
        return Optional.of(this.userRepository.save(user));
    }
}
