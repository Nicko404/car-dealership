package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.upsert.UpsertCarDto;
import ru.clevertec.entity.Car;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CarMapper;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.utils.ObjectsUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarShowroomRepository showroomRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CarDto> getAll() {
        return CarMapper.INSTANCE.carsToCarDtos(carRepository.findAll());
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Car with id {0} not found", id))
        );
        return CarMapper.INSTANCE.carToCarDto(car);
    }

    public CarDto create(UpsertCarDto carDto) {
        Car car = CarMapper.INSTANCE.upsertCarDtoToCar(carDto);
        car.setCarShowroom(showroomRepository.findById(carDto.getCarShowroomId()).orElseThrow(
                        () -> new EntityNotFoundException(MessageFormat.format("Showroom with id {0} not found", carDto.getCarShowroomId()))
                )
        );
        car.setCategory(categoryRepository.findById(carDto.getCategoryId()).orElseThrow(
                        () -> new EntityNotFoundException(MessageFormat.format("Category with id {0} not found", carDto.getCategoryId()))
                )
        );

        Car save = carRepository.save(car);
        return CarMapper.INSTANCE.carToCarDto(save);
    }

    @Transactional
    public CarDto update(Long id, UpsertCarDto carDto) {
        Car updated = CarMapper.INSTANCE.upsertCarDtoToCar(carDto);
        updated.setReviews(null);
        updated.setCarShowroom(showroomRepository.findById(carDto.getCarShowroomId()).orElseThrow(
                        () -> new EntityNotFoundException(MessageFormat.format("Showroom with id {0} not found", carDto.getCarShowroomId()))
                )
        );
        updated.setCategory(categoryRepository.findById(carDto.getCategoryId()).orElseThrow(
                        () -> new EntityNotFoundException(MessageFormat.format("Category with id {0} not found", carDto.getCategoryId()))
                )
        );

        Car saved = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Car with id {0} not found", id))
        );
        ObjectsUtils.copyNotNullProperties(updated, saved);

        carRepository.save(saved);

        return CarMapper.INSTANCE.carToCarDto(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(MessageFormat.format("Car with id {0} not found", id));
        }
    }
}
