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
import ru.clevertec.dto.BuyCarDto;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.dto.error.ErrorDto;
import ru.clevertec.dto.upsert.UpsertClientDto;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Validated
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody @Valid UpsertClientDto clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.create(clientDto));
    }

    @PostMapping("/buy")
    public ResponseEntity<ClientDto> buyCar(@RequestBody BuyCarDto dto) {
        return ResponseEntity.ok(clientService.buyCar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UpsertClientDto clientDto) {
        return ResponseEntity.ok(clientService.update(id, clientDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(e.getMessage()));
    }
}
