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

    /**
     * Проверяет, является ли пользователь с заданным tgId администратором.
     */
    public boolean isAdmin(Long tgId) {
        UserEntity user = userRepository.findByTgId(tgId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с tgId " + tgId + " не найден"));
        return adminRepository.existsByUser(user);
    }

    @Override
    public void save(AdminEntity adminEntity) {
        // Ensure user exists
        UserEntity user = userRepository.findById(adminEntity.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + adminEntity.getUser().getId() + " не найден"));
        if (adminRepository.existsByUser(user)) {
            throw new EntityExistsException("Пользователь уже является администратором");
        }
        adminEntity.setUser(user);
        adminRepository.save(adminEntity);
    }

    @Override
    public void remove(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new EntityNotFoundException("Админ с id " + id + " не найден");
        }
        adminRepository.deleteById(id);
    }

    @Override
    public AdminEntity get(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Админ с id " + id + " не найден"));
    }

    @Override
    public List<AdminEntity> getAll() {
        return adminRepository.findAll();
    }
}
