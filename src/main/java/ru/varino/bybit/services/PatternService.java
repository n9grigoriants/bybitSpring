package ru.varino.bybit.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.varino.bybit.entities.PatternEntity;
import ru.varino.bybit.repositories.PatternRepository;

import java.util.List;

@Service
public class PatternService implements ServiceInterface<PatternEntity> {

    private final PatternRepository patternRepository;

    @Autowired
    public PatternService(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    /**
     * Сохраняет или обновляет PatternEntity.
     * Если id == null — создаёт новую сущность.
     * Если id != null и запись существует — обновляет её.
     * Если id != null, но запись не найдена — кидает EntityNotFoundException.
     */
    @Override
    public void save(PatternEntity pattern) {
        Long id = pattern.getId();
        if (id == null) {
            // Новая запись
            patternRepository.save(pattern);
        } else {
            // Обновление существующей
            if (!patternRepository.existsById(id)) {
                throw new EntityNotFoundException("Паттерн с id " + id + " не найден");
            }
            patternRepository.save(pattern);
        }
    }

    /**
     * Удаляет шаблон по id, кидает EntityNotFoundException, если не найден.
     */
    @Override
    public void remove(Long id) {
        if (!patternRepository.existsById(id)) {
            throw new EntityNotFoundException("Паттерн с id " + id + " не найден");
        }
        patternRepository.deleteById(id);
    }

    /**
     * Возвращает Pattern по id или кидает EntityNotFoundException.
     */
    @Override
    public PatternEntity get(Long id) {
        return patternRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Паттерн с id " + id + " не найден"));
    }

    /**
     * Возвращает все шаблоны.
     */
    @Override
    public List<PatternEntity> getAll() {
        return patternRepository.findAll();
    }
}
