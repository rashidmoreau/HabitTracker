package habittracker.model;

public class PushNotification extends Notification {
    private String deviceToken;

    public PushNotification(String deviceToken, String message) {
        super(message);
        this.deviceToken = deviceToken;
    }

    @Override
    public void send() {
        // Real push logic would go here.
        System.out.println("Sending push to " + deviceToken + ": " + message);
    }
}
