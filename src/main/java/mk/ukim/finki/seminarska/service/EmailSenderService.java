package mk.ukim.finki.seminarska.service;

public interface EmailSenderService {
    public void sendEmail(String toEmail, String body, String subject);
}
