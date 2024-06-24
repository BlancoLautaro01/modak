package app.notification.service.ports;

import app.notification.exception.cases.InvalidParameterException;

public interface NotificationService {
    void send(String type, String message, String userId) throws InvalidParameterException;
}
