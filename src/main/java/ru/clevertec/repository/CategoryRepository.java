package ru.clevertec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    List<Category> findAll();

    @Override
    @EntityGraph("category_entity_graph")
    Optional<Category> findById(Long aLong);
}
