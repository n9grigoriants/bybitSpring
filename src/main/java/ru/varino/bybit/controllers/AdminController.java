package ru.varino.bybit.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.varino.bybit.entities.AdminEntity;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.repositories.UserRepository;
import ru.varino.bybit.services.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(AdminService adminService, UserRepository userRepository) {
        this.adminService = adminService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{tgId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdmin(@PathVariable Long tgId) {
        UserEntity user = userRepository.findByTgId(tgId)
                .orElseThrow(() -> new EntityNotFoundException("User with tgId " + tgId + " not found"));
        AdminEntity admin = new AdminEntity();
        admin.setUser(user);
        adminService.save(admin);
    }

    @GetMapping("/{id}")
    public AdminEntity getAdmin(@PathVariable Long id) {
        return adminService.get(id);
    }

    @GetMapping
    public List<AdminEntity> getAllAdmins() {
        return adminService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdmin(@PathVariable Long id) {
        adminService.remove(id);
    }
}
