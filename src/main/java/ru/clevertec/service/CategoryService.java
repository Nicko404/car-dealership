package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.entity.Category;
import ru.clevertec.repository.Repository;

import java.util.List;

@RequiredArgsConstructor
public class CategoryService implements Service<Category> {

    private final Repository<Category> repository;

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        return repository.update(id, category);
    }

    @Override
    public void delete(Category category) {
        repository.delete(category);
    }
}
