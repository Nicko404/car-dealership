package ru.clevertec.service;

import java.util.List;

public interface Service<T> {

    List<T> getAll();
    T getById(Long id);
    T save(T t);
    T update(Long id, T t);
    void delete(T t);
}
