package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.upsert.UpsertReviewDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.ReviewMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.utils.ObjectsUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        return ReviewMapper.INSTANCE.reviewsToReviewDtos(reviews);
    }

    public ReviewDto getById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Review with id {0} not found", id))
        );
        return ReviewMapper.INSTANCE.reviewToReviewDto(review);
    }

    public ReviewDto create(UpsertReviewDto reviewDto) {
        Review review = ReviewMapper.INSTANCE.upsertReviewDtoToReview(reviewDto);
        Car car = carRepository.findById(reviewDto.getCarId()).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Car with id {0} not found", reviewDto.getCarId()))
        );
        Client client = clientRepository.findById(reviewDto.getClientId()).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Client with id {0} not found", reviewDto.getClientId()))
        );

        review.setCar(car);
        review.setClient(client);

        review = reviewRepository.save(review);

        return ReviewMapper.INSTANCE.reviewToReviewDto(review);
    }

    public ReviewDto update(Long id, UpsertReviewDto reviewDto) {
        Review updated = ReviewMapper.INSTANCE.upsertReviewDtoToReview(reviewDto);
        Review saved = reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Review with id {0} not found", id))
        );

        ObjectsUtils.copyNotNullProperties(updated, saved);

        reviewRepository.save(saved);

        return ReviewMapper.INSTANCE.reviewToReviewDto(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            reviewRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(MessageFormat.format("Review with id {0} not found", id));
        }
    }
}
