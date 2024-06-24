package app.notification.controller;

import app.notification.exception.cases.InvalidParameterException;
import app.notification.model.inout.request.NotificationRequest;
import app.notification.service.ports.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
@Api(value = "", tags={"Notification Controller"})
@Tag(name = "Notification Controller", description = "Notification Methods")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<?> send(@Valid @RequestBody NotificationRequest notificationRequest) throws InvalidParameterException {
        notificationService.send(
                notificationRequest.getType(),
                notificationRequest.getMessage(),
                notificationRequest.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}