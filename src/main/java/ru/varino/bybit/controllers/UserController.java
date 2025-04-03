package ru.varino.bybit.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Tag(name = "post", description = "POST methods of API")
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user) {
        try {
            userService.save(user);
        } catch (EntityExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


        return ResponseEntity.ok("Пользователь сохранён");
    }
}
