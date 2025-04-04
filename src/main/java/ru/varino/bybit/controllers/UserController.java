package ru.varino.bybit.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.services.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Tag(name = "post", description = "POST methods of API")
    @Operation(summary = "Сохранить юзера", description = "Сохраняет юзера в бд (Любой, кто хоть раз написал /start)")
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user) {
        try {
            userService.save(user);
        } catch (EntityExistsException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok("Пользователь сохранён");
    }
}
