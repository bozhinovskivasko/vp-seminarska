package mk.ukim.finki.seminarska.service.impl;

import mk.ukim.finki.seminarska.model.User;
import mk.ukim.finki.seminarska.model.enumerations.Role;
import mk.ukim.finki.seminarska.model.exception.*;
import mk.ukim.finki.seminarska.repository.UserRepository;
import mk.ukim.finki.seminarska.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public void register(String username, String password, String repeatPassword, String name, String surname, Integer age, Role role) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();

        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();

        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, this.passwordEncoder.encode(password), name, surname, age, role);
        this.userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
