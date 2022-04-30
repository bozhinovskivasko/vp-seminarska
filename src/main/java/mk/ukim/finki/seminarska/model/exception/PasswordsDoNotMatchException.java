package mk.ukim.finki.seminarska.model.exception;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Passwords do mot match");
    }
}
