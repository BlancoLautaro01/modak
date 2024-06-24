package app.notification.model.enums;

import app.notification.exception.cases.InvalidParameterException;

import java.util.Arrays;

public enum NotificationTypeEnum {

    STATUS("STATUS"),
    NEW("NEW"),
    MARKETING("MARKETING");

    NotificationTypeEnum(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }

    public static void validateTypeByString(String type) throws InvalidParameterException {
        for(NotificationTypeEnum i: NotificationTypeEnum.values()) {
            if(i.getName().equals(type.toUpperCase())) {
                return;
            }
        }

        throw new InvalidParameterException("Invalid 'type' request" + type + ". " +
                "Available values are: " + Arrays.toString(NotificationTypeEnum.values()));
    }
}
