package pl.lechowicz.queansserver.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lechowicz.queansserver.common.exception.InputIncorrectException;
import pl.lechowicz.queansserver.user.modelDTO.NewUserDTO;
import pl.lechowicz.queansserver.user.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrationUser(@RequestBody NewUserDTO newUser) {
        try {
            userService.isValidNewUser(newUser);
            if (userService.isUserInDB(newUser.email())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This email exists in the database!");
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUser));
            }
        } catch (InputIncorrectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
