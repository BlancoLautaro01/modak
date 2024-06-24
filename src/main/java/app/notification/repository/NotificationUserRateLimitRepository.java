package app.notification.repository;

import app.notification.model.entities.NotificationUserRateLimit;
import app.notification.model.entities.RateLimitId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationUserRateLimitRepository extends CrudRepository<NotificationUserRateLimit, RateLimitId> {

}
