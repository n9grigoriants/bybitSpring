package ru.varino.bybit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.varino.bybit.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByTgId(Long tgId);
    Optional<UserEntity> findByTgId(Long tgId);
}
