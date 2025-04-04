package ru.varino.bybit.services;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.varino.bybit.entities.AdminEntity;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.repositories.AdminRepository;
import ru.varino.bybit.repositories.UserRepository;

import java.util.List;


@Service
public class AdminService implements ServiceInterface<AdminEntity> {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean isAdmin(Long tgId) {
        UserEntity user = userRepository.findByTgId(tgId).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return adminRepository.existsByUser(user);


    }

    @Override
    public void save(AdminEntity adminEntity) {
        UserEntity user = userRepository.findById(adminEntity.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"))
        if (adminRepository.existsByUser(user)) {
            throw new EntityExistsException("Пользователь уже является админом");
        }
        adminRepository.save(adminEntity);

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public AdminEntity get(Long id) {
        return null;
    }

    @Override
    public List<AdminEntity> getAll() {
        return List.of();
    }
}
