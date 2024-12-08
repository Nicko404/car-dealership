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
import ru.clevertec.dto.CarShowroomDto;
import ru.clevertec.dto.error.ErrorDto;
import ru.clevertec.dto.AddCarToShowroomDto;
import ru.clevertec.dto.upsert.UpsertCarShowroomDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.service.CarShowroomService;

import java.util.List;

@RestController
@RequestMapping("/carShowrooms")
@Validated
@RequiredArgsConstructor
public class CarShowroomController {

    private final CarShowroomService carShowroomService;

    @GetMapping
    public ResponseEntity<List<CarShowroomDto>> getCarShowrooms() {
        return ResponseEntity.ok(carShowroomService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarShowroomDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(carShowroomService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CarShowroomDto> create(@RequestBody @Valid UpsertCarShowroomDto carShowroomDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carShowroomService.create(carShowroomDto));
    }

    @PostMapping("/add")
    public ResponseEntity<CarShowroomDto> addCarToShowroom(@RequestBody @Valid AddCarToShowroomDto addCarToShowroomDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carShowroomService.addCarToShowroom(addCarToShowroomDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarShowroomDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UpsertCarShowroomDto carShowroomDto) {
        return ResponseEntity.ok(carShowroomService.update(id, carShowroomDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        carShowroomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(e.getMessage()));
    }
}
