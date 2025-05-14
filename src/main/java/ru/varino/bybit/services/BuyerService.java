package ru.varino.bybit.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.varino.bybit.entities.BuyerEntity;
import ru.varino.bybit.entities.UserEntity;
import ru.varino.bybit.repositories.BuyerRepository;
import ru.varino.bybit.repositories.UserRepository;
import ru.varino.bybit.services.ServiceInterface;

import java.util.List;

@Service
public class BuyerService implements ServiceInterface<BuyerEntity> {

    private final BuyerRepository buyerRepository;
    private final UserRepository userRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository, UserRepository userRepository) {
        this.buyerRepository = buyerRepository;
        this.userRepository = userRepository;
    }

    /**
     * Сохраняет или обновляет объект BuyerEntity. Обеспечивает загрузку (управление)
     * ссылающегося UserEntity из базы данных, чтобы избежать ошибки отсоединения сущности.
     */
    @Override
    public void save(BuyerEntity buyer) {
        // Must provide a user reference
        if (buyer.getUser() == null || buyer.getUser().getId() == null) {
            throw new IllegalArgumentException("Покупатель должен ссылаться на существующего пользователя по ID");
        }

        Long userId = buyer.getUser().getId();
        UserEntity managedUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + userId + " не найден"));

        // Associate the managed user instance
        buyer.setUser(managedUser);

        buyerRepository.save(buyer);
    }

    /**
     * Удаляет покупателя по его ID, выбрасывая его, если он не найден.
     */
    @Override
    public void remove(Long id) {
        if (!buyerRepository.existsById(id)) {
            throw new EntityNotFoundException("Покупатель с id " + id + " не найден");
        }
        buyerRepository.deleteById(id);
    }

    /**
     * Получает одного покупателя по ID или отбрасывает, если его нет.
     */
    @Override
    public BuyerEntity get(Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Покупатель с id " + id + " не найден"));
    }

    /**
     * Возвращает всех покупателей.
     */
    @Override
    public List<BuyerEntity> getAll() {
        return buyerRepository.findAll();
    }
}