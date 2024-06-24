package app.notification.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@IdClass(RateLimitId.class)
@Table(name = "notification_user_rate_limit")
public class NotificationUserRateLimit {

    @Id
    private String userId;

    @Id
    private String type;

    @Column
    private String period;

    @Column
    private Integer rateLimit;
}
