package ru.varino.bybit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.varino.bybit.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByTgId(Long tgId);
}
