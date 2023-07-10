package com.example.syncapp.rest;

import com.example.syncapp.entity.User;
import com.example.syncapp.exceptions.DefaultException;
import com.example.syncapp.rest.defaults.Response;
import com.example.syncapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public ResponseEntity<Response> signUp(@RequestBody User user) {
        try {
            User createdUser = userService.signUp(user.getEmail(), user.getPassword(), user.getName());
            Response response = new Response();
            response.addProperty("user", createdUser);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (DefaultException defaultException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response().setError(defaultException));
        }
    }


    @PostMapping("login")
    public ResponseEntity<Response> login(@RequestBody User user) {
        try {
            User createdUser = userService.login(user.getId(), user.getPassword());
            Response response = new Response();
            response.addProperty("user", createdUser);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (DefaultException defaultException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response().setError(defaultException));
        }
    }

}
