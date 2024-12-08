package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.upsert.UpsertCarDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Review;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings({
            @Mapping(source = "category.id", target = "categoryId"),
            @Mapping(source = "carShowroom.id", target = "carShowroomId")

    })
    CarDto carToCarDto(Car car);

    List<CarDto> carsToCarDtos(List<Car> cars);

    Car upsertCarDtoToCar(UpsertCarDto carDto);

    default List<ReviewDto> reviewsToReviewDtos(List<Review> review) {
        return Mappers.getMapper(ReviewMapper.class).reviewsToReviewDtos(review);
    }
}
