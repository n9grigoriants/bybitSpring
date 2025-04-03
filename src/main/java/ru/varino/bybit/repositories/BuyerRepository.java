package ru.varino.bybit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.varino.bybit.entities.BuyerEntity;

public interface BuyerRepository extends JpaRepository<BuyerEntity, Long> {
}
