package ru.varino.bybit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.varino.bybit.entities.SessionEntity;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
}
