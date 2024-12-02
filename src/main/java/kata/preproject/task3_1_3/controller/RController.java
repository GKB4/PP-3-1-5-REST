package kata.preproject.task3_1_3.controller;

import jakarta.validation.Valid;
import kata.preproject.task3_1_3.exception_handling.NoSuchUserException;
import kata.preproject.task3_1_3.exception_handling.UserNotCreatedException;
import kata.preproject.task3_1_3.model.User;
import kata.preproject.task3_1_3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RController {

    private final UserService userService;

    public RController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("User with id " + id + " not found");
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errors.append(fieldError.getField() + " " + fieldError.getDefaultMessage());
            }
            throw new UserNotCreatedException(errors.toString());
        }
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errors.append(fieldError.getField() + " " + fieldError.getDefaultMessage());
            }
            throw new UserNotCreatedException(errors.toString());
        }
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("User with id " + id + " not found");
        }
        userService.deleteUser(id);
        return "User with id " + id + " deleted";
    }

    @GetMapping("/whoiam")
    public User getCurrentUserName(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUser(userDetails.getUsername());
    }
}
