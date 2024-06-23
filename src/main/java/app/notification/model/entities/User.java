package app.notification.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    @Length(min = 3, max = 30, message = "The name must have a minimum of 3 and a maximum of 30 characters")
    private String name;

    @Column(nullable = false)
    @Length(min = 3, max = 30, message = "The surname must have a minimum of 3 and a maximum of 30 characters")
    private String surname;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\-]]).{6,}$", message = "Password must contain at least 1 lowercase, 1 uppercase, 1 special character and a minimum of 6 characters")
    private String password;

    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email")
    private String email;

    @Column(nullable = false)
    @Length(min = 10, max = 30, message = "The address must have a minimum of 10 and a maximum of 30 characters")
    private String address;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lon;

}
