package app.notification.controller;

import app.notification.exception.cases.UserNotFoundException;
import app.notification.model.entities.User;
import app.notification.model.inout.request.LoginRequest;
import app.notification.model.inout.dto.LoginDto;
import app.notification.model.inout.request.UserRequest;
import app.notification.service.ports.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "", tags={"User Controller"})
@Tag(name = "User Controller", description = "User Methods")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.register(userRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<LoginDto> login(@Valid @RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }
}