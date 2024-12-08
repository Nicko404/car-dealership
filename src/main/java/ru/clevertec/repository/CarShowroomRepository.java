package ru.clevertec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.CarShowroom;

import java.util.Optional;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {

    @Override
    @EntityGraph("car_showroom_entity_graph")
    Optional<CarShowroom> findById(Long aLong);
}
