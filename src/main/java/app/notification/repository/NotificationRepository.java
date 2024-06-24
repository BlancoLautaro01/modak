package app.notification.repository;

import app.notification.model.entities.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    @Query(value = "SELECT COUNT(n.*) FROM notification n" +
            " WHERE n.type = :type AND n.user_id = :userId AND " +
            " :queryFilter", nativeQuery = true)
    Integer getCountByTypeUserAndPeriod(String type, String userId, String queryFilter);
}