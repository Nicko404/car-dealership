package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.CarShowroomDto;
import ru.clevertec.dto.upsert.UpsertCarShowroomDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarShowroomMapper {

    CarShowroomMapper INSTANCE = Mappers.getMapper(CarShowroomMapper.class);

    CarShowroomDto carShowroomToCarShowroomDto(CarShowroom carShowroom);

    List<CarShowroomDto> carShowroomsToCarShowroomDtos(List<CarShowroom> carShowrooms);

    CarShowroom carShowroomDtoToCarShowroom(UpsertCarShowroomDto carShowroomDto);

    default List<CarDto> carsToCarDtos(List<Car> cars) {
        return Mappers.getMapper(CarMapper.class).carsToCarDtos(cars);
    }
}
