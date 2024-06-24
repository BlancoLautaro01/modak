package app.notification.service.impl;

import org.springframework.stereotype.Service;

@Service
public class Gateway {
    public void send(String userId, String message) {
        System.out.println("sending message to user " + userId);
    }
}
