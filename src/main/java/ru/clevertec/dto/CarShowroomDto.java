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
public class CarShowroomDto {

    private Long id;

    private String name;

    private String address;

    @Builder.Default
    private List<CarDto> cars = new ArrayList<>();
}
