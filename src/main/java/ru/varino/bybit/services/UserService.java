package ru.varino.bybit.services;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;



    public void save(UserEntity user) {
        if (userRepository.existsByTgId(user.getTgId())) {
            throw new EntityExistsException("User with id " + user.getId() + " already exists");
        }
        userRepository.save(user);
    }


}
