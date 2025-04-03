package ru.varino.bybit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.varino.bybit.entities.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
}
