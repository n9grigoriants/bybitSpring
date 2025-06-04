package ru.varino.bybit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.varino.bybit.entities.AdminEntity;
import ru.varino.bybit.entities.UserEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    boolean existsByUser(UserEntity user);
}
