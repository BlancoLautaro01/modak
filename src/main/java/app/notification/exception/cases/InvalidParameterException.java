package app.notification.exception.cases;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidParameterException extends Exception {

    private String message;

    public String getMessage() {
        return this.message;
    }
}
