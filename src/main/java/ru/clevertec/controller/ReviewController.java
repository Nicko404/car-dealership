package ru.clevertec.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.ReviewDto;
import ru.clevertec.dto.error.ErrorDto;
import ru.clevertec.dto.upsert.UpsertReviewDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Validated
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() {
        return ResponseEntity.ok(reviewService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(reviewService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody UpsertReviewDto reviewDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.create(reviewDto));
    }

    @PostMapping("/add")
    public ResponseEntity<ReviewDto> addReview(@RequestBody @Valid UpsertReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.create(reviewDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UpsertReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.update(id, reviewDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(e.getMessage()));
    }
}
