package ru.clevertec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Override
    @EntityGraph("car_entity_graph")
    Optional<Car> findById(Long aLong);

    @Override
    @EntityGraph("car_entity_graph")
    List<Car> findAll();
}
