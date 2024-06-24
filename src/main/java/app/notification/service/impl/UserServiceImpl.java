package app.notification.service.impl;

import app.notification.repository.UserRepository;
import app.notification.exception.cases.UserNotFoundException;
import app.notification.model.entities.User;
import app.notification.model.inout.request.LoginRequest;
import app.notification.model.inout.dto.LoginDto;
import app.notification.model.inout.request.UserRequest;
import app.notification.service.ports.UserService;
import app.notification.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public LoginDto login(LoginRequest loginRequest) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Invalid Email or Password"));

        return new LoginDto(user.getName(), jwtUtil.getJWTToken(user.getUserId().toString()));
    }

    public User register(UserRequest userRequest) {
        User user = new User(
            null,
            userRequest.getName(),
            userRequest.getSurname(),
            userRequest.getPassword(),
            userRequest.getEmail()
        );

        return userRepository.save(user);
    }

    public User getLoggedUser() throws UserNotFoundException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findById(Integer.valueOf(userId))
                .orElseThrow(() -> new UserNotFoundException("Invalid UserId"));
    }
}
