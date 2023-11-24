package com.zira.codingtask.controller;

import com.zira.codingtask.controller.model.AccessTokenDTO;
import com.zira.codingtask.controller.model.CreateUserBody;
import com.zira.codingtask.controller.model.UpdateUserBody;
import com.zira.codingtask.controller.model.UserDTO;
import com.zira.codingtask.exception.InvalidEmailOrPasswordException;
import com.zira.codingtask.exception.ValidationException;
import com.zira.codingtask.exception.ValidationIdException;

import com.zira.codingtask.repository.model.UserLogin;
import com.zira.codingtask.service.LoginService;
import com.zira.codingtask.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<Object> listUserById(@PathVariable("userId") Long userId) {
        try {
            UserDTO listUserId = userService.getUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(listUserId);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createNewUser(@RequestBody CreateUserBody createUserBody) {
        try {
            UserDTO user = userService.createUser(createUserBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "token")
    public ResponseEntity<Object> getToken(@RequestBody UserLogin userLogin) {
        try {
            AccessTokenDTO loginUser = loginService.loginUser(userLogin);
            return ResponseEntity.status(HttpStatus.CREATED).body(loginUser);
        } catch (InvalidEmailOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userId);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") Long userId,
                                             @RequestBody UpdateUserBody updateUserBody) {
        try {
            UserDTO updatedUser = userService.updateUser(userId, updateUserBody);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedUser);
        } catch (ValidationIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
