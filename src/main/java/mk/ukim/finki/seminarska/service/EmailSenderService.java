package mk.ukim.finki.seminarska.service;

public interface EmailSenderService {
    void sendEmail(String toEmail, String body, String subject);
}
