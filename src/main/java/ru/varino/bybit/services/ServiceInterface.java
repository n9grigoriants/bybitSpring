package ru.varino.bybit.services;

import java.util.List;

public interface ServiceInterface<T> {
    void save(T t);

    void remove(Long id);

    T get(Long id);

    List<T> getAll();

}
