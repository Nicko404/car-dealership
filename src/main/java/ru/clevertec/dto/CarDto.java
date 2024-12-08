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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    private Long id;

    private String model;

    private String brand;

    private Integer manufactureDate;

    private Integer price;

    private Long categoryId;

    private Long carShowroomId;

    @Builder.Default
    private List<ReviewDto> reviews = new ArrayList<>();
}
