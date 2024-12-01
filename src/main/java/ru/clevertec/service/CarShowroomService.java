package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.repository.Repository;

import java.util.List;

@RequiredArgsConstructor
public class CarShowroomService implements Service<CarShowroom> {

    private final Repository<CarShowroom> repository;

    @Override
    public List<CarShowroom> getAll() {
        return repository.findAll();
    }

    @Override
    public CarShowroom getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CarShowroom save(CarShowroom carShowroom) {
        return repository.save(carShowroom);
    }

    @Override
    public CarShowroom update(Long id, CarShowroom carShowroom) {
        return repository.update(id, carShowroom);
    }

    @Override
    public void delete(CarShowroom carShowroom) {
        repository.delete(carShowroom);
    }

    public void assignCarToShowroom(Car car, CarShowroom carShowroom) {
        car.setCarShowroom(null);
        carShowroom.addCar(car);
        ((CarShowroomRepository)repository).assignCarToShowroom(car, carShowroom);
    }
}
