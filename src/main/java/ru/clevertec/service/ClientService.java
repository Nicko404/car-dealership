package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.BuyCarDto;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.dto.upsert.UpsertClientDto;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.Client;
import ru.clevertec.exception.EntityNotFoundException;
import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.repository.ClientRepository;
import ru.clevertec.utils.ObjectsUtils;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public List<ClientDto> getAll() {
        List<Client> clients = clientRepository.findAll();
        return ClientMapper.INSTANCE.clientsToClientDtos(clients);
    }

    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Client with id {0} not found", id))
        );
        return ClientMapper.INSTANCE.clientToClientDto(client);
    }

    public ClientDto create(UpsertClientDto clientDto) {
        Client client = ClientMapper.INSTANCE.upsertClientDtoToClient(clientDto);
        client.setRegistrationDate(LocalDate.now());
        return ClientMapper.INSTANCE.clientToClientDto(clientRepository.save(client));
    }

    public ClientDto buyCar(BuyCarDto dto) {
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Client {0} not found", dto.getClientId()))
        );

        Car car = carRepository.findById(dto.getCarId()).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Car {0} not found", dto.getCarId()))
        );

        client.addCar(car);
        car.setCarShowroom(null);
        clientRepository.save(client);
        carRepository.save(car);
        return ClientMapper.INSTANCE.clientToClientDto(client);
    }

    public ClientDto update(Long id, UpsertClientDto clientDto) {
        Client updated = ClientMapper.INSTANCE.upsertClientDtoToClient(clientDto);
        updated.setCars(null);
        updated.setReviews(null);
        Client saved = clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Client with id {0} not found", id))
        );

        ObjectsUtils.copyNotNullProperties(updated, saved);

        return ClientMapper.INSTANCE.clientToClientDto(clientRepository.save(saved));
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException(MessageFormat.format("Client with id {0} not found", id));
        }
    }


}
