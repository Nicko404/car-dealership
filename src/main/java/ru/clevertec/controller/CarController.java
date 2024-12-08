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
import ru.clevertec.dto.CarDto;
import ru.clevertec.dto.error.ErrorDto;
import ru.clevertec.dto.upsert.UpsertCarDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
@Validated
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PostMapping
    public ResponseEntity<CarDto> create(@RequestBody @Valid UpsertCarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carService.create(carDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UpsertCarDto carDto) {
        return ResponseEntity.ok(carService.update(id, carDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }
}
