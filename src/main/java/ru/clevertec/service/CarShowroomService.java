package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CarShowroomDto;
import ru.clevertec.dto.AddCarToShowroomDto;
import ru.clevertec.dto.upsert.UpsertCarShowroomDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.CarShowroomMapper;
import ru.clevertec.repository.CarShowroomRepository;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.utils.ObjectsUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarShowroomService {

    private final CarShowroomRepository showroomRepository;
    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public List<CarShowroomDto> getAll() {
        List<CarShowroom> showrooms = showroomRepository.findAll();
        return CarShowroomMapper.INSTANCE.carShowroomsToCarShowroomDtos(showrooms);
    }

    public CarShowroomDto getById(Long id) {
        CarShowroom carShowroom = showroomRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Showroom with id {0} not found", id))
        );
        return CarShowroomMapper.INSTANCE.carShowroomToCarShowroomDto(carShowroom);
    }

    public CarShowroomDto create(UpsertCarShowroomDto carShowroomDto) {
        CarShowroom carShowroom = CarShowroomMapper.INSTANCE.carShowroomDtoToCarShowroom(carShowroomDto);
        return CarShowroomMapper.INSTANCE.carShowroomToCarShowroomDto(showroomRepository.save(carShowroom));
    }

    public CarShowroomDto addCarToShowroom(AddCarToShowroomDto dto) {
        Car car = carRepository.findById(dto.getCarId()).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Car with id {0} not found", dto.getCarId()))
        );
        CarShowroom carShowroom = showroomRepository.findById(dto.getShowroomId()).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Showroom with id {0} not found", dto.getShowroomId()))
        );

        carShowroom.addCar(car);

        return CarShowroomMapper.INSTANCE.carShowroomToCarShowroomDto(showroomRepository.save(carShowroom));
    }

    public CarShowroomDto update(Long id, UpsertCarShowroomDto carShowroomDto) {
        CarShowroom updated = CarShowroomMapper.INSTANCE.carShowroomDtoToCarShowroom(carShowroomDto);
        updated.setCars(null);
        CarShowroom saved = showroomRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Showroom with id {0} not found", id))
        );

        ObjectsUtils.copyNotNullProperties(updated, saved);

        showroomRepository.save(saved);

        return CarShowroomMapper.INSTANCE.carShowroomToCarShowroomDto(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            showroomRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(MessageFormat.format("Showroom with id {0} not found", id));
        }
    }
}
