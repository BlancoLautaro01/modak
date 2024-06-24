package app.notification.service.impl;

import app.notification.exception.cases.InvalidParameterException;
import app.notification.model.entities.Notification;
import app.notification.model.entities.NotificationUserRateLimit;
import app.notification.model.entities.RateLimitId;
import app.notification.model.enums.NotificationTypeEnum;
import app.notification.model.enums.PeriodTypeEnum;
import app.notification.repository.NotificationRepository;
import app.notification.repository.NotificationUserRateLimitRepository;
import app.notification.service.ports.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationUserRateLimitRepository notificationUserRateLimitRepository;

    @Autowired
    private Gateway gateway;

    public void send(String type, String message, String userId) throws InvalidParameterException {
        // Validate 'type' and get user 'rate'.
        NotificationTypeEnum.validateTypeByString(type);
        Optional<NotificationUserRateLimit> rate = notificationUserRateLimitRepository.findById(
                        RateLimitId.builder().type(type).userId(userId).build());

        // If 'rate' exists first validate the limit, if not just send the message.
        if(rate.isPresent()) {
            this.validateRateAndSend(rate.get(), message);
        } else {
            this.sendAndSaveNotification(type, message, userId);
        }
    }

    private void validateRateAndSend(NotificationUserRateLimit rate, String message) throws InvalidParameterException {
        // Validate 'period' and get the count of notification to that user.
        PeriodTypeEnum period = PeriodTypeEnum.fromString(rate.getPeriod());
        Integer notificationCount =
                notificationRepository.getCountByTypeUserAndPeriod(
                        rate.getType(),
                        rate.getUserId(),
                        period.getQueryFilter());

        // Validate limit and send. If not limit is exceeded, do nothing.
        if(notificationCount.compareTo(rate.getRateLimit()) < 0) {
            this.sendAndSaveNotification(rate.getType(), message, rate.getUserId());
        }
    }

    private void sendAndSaveNotification(String type, String message, String userId) {
        // Send message
        gateway.send(userId, message);

        // Save Notification entity.
        Notification notification = Notification.builder()
                .userId(userId)
                .type(type)
                .creationDate(LocalDateTime.now()).build();
        notificationRepository.save(notification);
    }


}
