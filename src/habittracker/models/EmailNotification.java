package habittracker.models;

public class EmailNotification extends Notification {
    private String toAddress;
    private String subject;

    public EmailNotification(String toAddress, String subject, String message) {
        super(message);
        this.toAddress = toAddress;
        this.subject = subject;
    }

    @Override
    public void send() {
        System.out.println("Sending email to " + toAddress + ": " + subject + " - " + message);
    }
}
