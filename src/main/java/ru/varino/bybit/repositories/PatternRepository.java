package ru.varino.bybit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.varino.bybit.entities.PatternEntity;


public interface PatternRepository extends JpaRepository<PatternEntity, Long> {
}
