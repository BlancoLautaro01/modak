package app.notification;

import app.notification.model.entities.NotificationUserRateLimit;
import app.notification.repository.NotificationRepository;
import app.notification.repository.NotificationUserRateLimitRepository;
import app.notification.service.impl.Gateway;
import app.notification.service.impl.NotificationServiceImpl;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private NotificationUserRateLimitRepository notificationUserRateLimitRepository;
    @Mock
    private Gateway gateway;
    @InjectMocks
    private NotificationServiceImpl notificationService;


    @Test
    void shouldSendNotificationWhenNoRate() throws Exception {
        String userId = "USER1";
        String type = "STATUS";
        String message = "Hello!";

        when(notificationUserRateLimitRepository.findById(any())).thenReturn(Optional.empty());

        notificationService.send(type, message, userId);
        verify(gateway, times(1)).send(userId, message);
    }

    @Test
    void shouldSendNotificationWhenLimitIsOk() throws Exception {
        String userId = "USER1";
        String type = "STATUS";
        String message = "Hello!";

        when(notificationUserRateLimitRepository.findById(any())).thenReturn(Optional.of(
                NotificationUserRateLimit.builder().userId(userId).type(type).period("MONTH").rateLimit(1).build()));

        when(notificationRepository.getCountByTypeUserAndPeriod(eq(type), eq(userId), any())).thenReturn(0);

        notificationService.send(type, message, userId);
        verify(gateway, times(1)).send(userId, message);
    }

    @Test
    void shouldNOTSendNotificationWhenLimitIsExceeded() throws Exception {
        String userId = "USER1";
        String type = "STATUS";
        String message = "Hello!";

        when(notificationUserRateLimitRepository.findById(any())).thenReturn(Optional.of(
                NotificationUserRateLimit.builder().userId(userId).type(type).period("MONTH").rateLimit(1).build()));

        when(notificationRepository.getCountByTypeUserAndPeriod(eq(type), eq(userId), any())).thenReturn(1);

        notificationService.send(type, message, userId);
        verify(gateway, times(0)).send(userId, message);
    }
}
