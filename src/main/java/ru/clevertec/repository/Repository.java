package ru.clevertec.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();
    T findById(Long id);
    T save(T t);
    T update(Long id, T t);
    void delete(T t);
}
