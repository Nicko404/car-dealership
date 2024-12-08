package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.upsert.UpsertCategoryDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

    List<CategoryDto> categoriesToCategoryDtos(List<Category> categories);

    Category upsertCategoryDtoToCategory(UpsertCategoryDto categoryDto);

    default List<CarDto> carsToCatDtos(List<Car> cars) {
        return CarMapper.INSTANCE.carsToCarDtos(cars);
    }
}
