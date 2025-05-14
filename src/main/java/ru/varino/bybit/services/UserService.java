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

    /**
     * Сохраняет нового пользователя.
     * Бросает EntityExistsException, если tgId уже существует.
     */
    @Override
    public void save(UserEntity user) {
        if (userRepository.existsByTgId(user.getTgId())) {
            throw new EntityExistsException(
                    "Пользователь с tgId " + user.getTgId() + " уже существует"
            );
        }
        userRepository.save(user);
    }

    /**
     * Удаляет пользователя по его internal ID.
     * Бросает EntityNotFoundException, если пользователь не найден.
     */
    @Override
    public void remove(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Пользователь с id " + id + " не найден"
            );
        }
        userRepository.deleteById(id);
    }

    /**
     * Возвращает пользователя по internal ID.
     * Бросает EntityNotFoundException, если пользователь не найден.
     */
    @Override
    public UserEntity get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Пользователь с id " + id + " не найден"
                ));
    }

    /**
     * Возвращает список всех пользователей.
     */
    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
