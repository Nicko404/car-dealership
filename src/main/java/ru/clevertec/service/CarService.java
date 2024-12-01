package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.entity.Car;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.Repository;

import java.util.List;

@RequiredArgsConstructor
public class CarService implements Service<Car>{

    private final Repository<Car> repository;

    @Override
    public List<Car> getAll() {
        return repository.findAll();
    }

    public List<Car> getCarsByFilter(String brand, String category, int year, Integer minPrice, Integer maxPrice, boolean ascOrderByPrice) {
        return ((CarRepository)repository).findByFilter(brand, category, year, minPrice, maxPrice, ascOrderByPrice);
    }

    public List<Car> getAll(int pageCount, int pageSize) {
        return ((CarRepository)repository).findAll(pageCount, pageSize);
    }

    @Override
    public Car getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }

    @Override
    public Car update(Long id, Car car) {
        return repository.update(id, car);
    }

    @Override
    public void delete(Car car) {
        repository.delete(car);
    }
}
