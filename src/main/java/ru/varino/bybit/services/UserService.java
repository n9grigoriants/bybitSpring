package ru.varino.bybit.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements ServiceInterface<UserEntity> {
    @Autowired
    private UserRepository userRepository;

    public void save(UserEntity user) {
        if (userRepository.existsByTgId(user.getTgId())) {
            throw new EntityExistsException("Пользователь с id " + user.getTgId() + " уже существует");
        }
        userRepository.save(user);
    }

    @Override
    public void remove(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Пользователя с id " + userId + " не существует");
        }
        userRepository.deleteById(userId);

    }

    @Override
    public UserEntity get(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }


}
