package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

    private Long id;

    private String name;

    @Builder.Default
    private List<String> contacts = new ArrayList<>();

    private String registrationDate;

    @Builder.Default
    private List<CarDto> cars = new ArrayList<>();

    @Builder.Default
    private List<ReviewDto> reviews = new ArrayList<>();
}
