package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.entity.Review;
import ru.clevertec.repository.Repository;

import java.util.List;

@RequiredArgsConstructor
public class ReviewService implements Service<Review> {

    private final Repository<Review> repository;

    @Override
    public List<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public Review getById(Long id) {
        return repository.findById(id);
    }

    public Review addReview(Client client, Car car, String text, Integer rating) {
        Review review = Review.builder()
                .car(car)
                .client(client)
                .rating(rating)
                .content(text)
                .build();

        repository.save(review);
        return review;
    }

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public Review update(Long id, Review review) {
        return repository.update(id, review);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }
}
