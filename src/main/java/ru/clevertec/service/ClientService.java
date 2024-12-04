package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.ClientRepository;

import java.util.List;

@RequiredArgsConstructor
public class ClientService implements Service<Client> {

    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        return clientRepository.update(id, client);
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    public Client buyCar(Client client, Car car) {
        client.addCar(car);
        car.setCarShowroom(null);
        clientRepository.update(client.getId(), client);
        carRepository.update(car.getId(), car);
        return client;
    }
}
