package ru.varino.bybit.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.varino.bybit.entities.BuyerEntity;
import ru.varino.bybit.entities.SessionEntity;
import ru.varino.bybit.repositories.BuyerRepository;
import ru.varino.bybit.repositories.SessionRepository;

import java.util.List;

@Service
public class SessionService implements ServiceInterface<SessionEntity> {

    private final SessionRepository sessionRepository;
    private final BuyerRepository buyerRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, BuyerRepository buyerRepository) {
        this.sessionRepository = sessionRepository;
        this.buyerRepository  = buyerRepository;
    }

    /**
     * Сохраняет или обновляет SessionEntity.
     * При сохранении привязывает managed BuyerEntity, чтобы избежать ошибки detached entity.
     */
    @Override
    public void save(SessionEntity sessionEntity) {
        if (sessionEntity.getBuyer() == null || sessionEntity.getBuyer().getId() == null) {
            throw new IllegalArgumentException("Сессия должна ссылаться на существующего покупателя по id");
        }
        Long buyerId = sessionEntity.getBuyer().getId();
        BuyerEntity managedBuyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new EntityNotFoundException("Покупатель с id " + buyerId + " не найден"));

        sessionEntity.setBuyer(managedBuyer);
        sessionRepository.save(sessionEntity);
    }

    /**
     * Удаляет Session по id, кидает если не найден.
     */
    @Override
    public void remove(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new EntityNotFoundException("Сессия с id " + id + " не найдена");
        }
        sessionRepository.deleteById(id);
    }

    /**
     * Возвращает Session по id или кидает.
     */
    @Override
    public SessionEntity get(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сессия с id " + id + " не найдена"));
    }

    /**
     * Возвращает все Session.
     */
    @Override
    public List<SessionEntity> getAll() {
        return sessionRepository.findAll();
    }
}
