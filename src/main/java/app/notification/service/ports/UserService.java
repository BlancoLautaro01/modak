package app.notification.service.ports;

import app.notification.exception.cases.UserNotFoundException;
import app.notification.model.entities.User;
import app.notification.model.inout.dto.LoginDto;
import app.notification.model.inout.request.LoginRequest;
import app.notification.model.inout.request.UserRequest;

public interface UserService {
    public LoginDto login(LoginRequest loginRequest) throws UserNotFoundException;
    public User register(UserRequest userRequest);
    public User getLoggedUser() throws UserNotFoundException;
}
