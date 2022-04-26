package mk.ukim.finki.seminarska.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PasswordDoNotMatchException extends RuntimeException {
    public PasswordDoNotMatchException() {
        super("Passwords do not match!");
    }
}
