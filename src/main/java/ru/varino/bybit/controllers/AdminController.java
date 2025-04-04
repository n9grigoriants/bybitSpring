package ru.varino.bybit.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.varino.bybit.entities.AdminEntity;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.services.AdminService;
import ru.varino.bybit.services.UserService;

@RestController
@RequestMapping("/api/admins/{tgId}")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity checkIfAdmin(@PathVariable Long tgId) {
        try {
            return ResponseEntity.ok(adminService.isAdmin(tgId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<String> addAdmin(@PathVariable Long tgId) {
        try {
            adminService.save();
        }

    }

}
