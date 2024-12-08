package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.upsert.UpsertCategoryDto;
import ru.clevertec.entity.Category;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CategoryMapper;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.utils.ObjectsUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return CategoryMapper.INSTANCE.categoriesToCategoryDtos(categoryRepository.findAll());
    }

    public CategoryDto getById(Long id) {
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Category with id {0} not found", id))
                )
        );
    }

    public CategoryDto create(UpsertCategoryDto categoryDto) {
        Category save = categoryRepository.save(CategoryMapper.INSTANCE.upsertCategoryDtoToCategory(categoryDto));
        return CategoryMapper.INSTANCE.categoryToCategoryDto(save);
    }

    public CategoryDto update(Long id, UpsertCategoryDto categoryDto) {
        Category updated = CategoryMapper.INSTANCE.upsertCategoryDtoToCategory(categoryDto);
        updated.setCars(null);
        Category saved = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Category with id {0} not found", id))
        );

        ObjectsUtils.copyNotNullProperties(updated, saved);

        categoryRepository.save(saved);

        return CategoryMapper.INSTANCE.categoryToCategoryDto(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(MessageFormat.format("Category with id {0} not found", id));
        }
    }
}
