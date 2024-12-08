package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.upsert.UpsertClientDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto clientToClientDto(Client client);

    Client upsertClientDtoToClient(UpsertClientDto clientDto);

    List<ClientDto> clientsToClientDtos(List<Client> clientList);

    default List<ReviewDto> reviewsToReviewDtos(List<Review> reviewList) {
        return Mappers.getMapper(ReviewMapper.class).reviewsToReviewDtos(reviewList);
    }

    default List<CarDto> carsToCarDtos(List<Car> carList) {
        return Mappers.getMapper(CarMapper.class).carsToCarDtos(carList);
    }

}
