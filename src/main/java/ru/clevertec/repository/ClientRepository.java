package ru.clevertec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Override
    List<Client> findAll();

    @Override
    @EntityGraph("client_entity_graph")
    Optional<Client> findById(Long aLong);
}
