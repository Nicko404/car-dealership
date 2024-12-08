package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.upsert.UpsertReviewDto;
import ru.clevertec.entity.Review;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mappings({
            @Mapping(source = "client.id", target = "clientId"),
            @Mapping(source = "car.id", target = "carId")
    })
    ReviewDto reviewToReviewDto(Review review);

    Review upsertReviewDtoToReview(UpsertReviewDto dto);

    List<ReviewDto> reviewsToReviewDtos(List<Review> reviews);
}
