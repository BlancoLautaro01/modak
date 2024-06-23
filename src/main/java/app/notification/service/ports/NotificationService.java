package app.notification.service.ports;

public interface NotificationService {
    void send(String type, String message, String userId);
}
