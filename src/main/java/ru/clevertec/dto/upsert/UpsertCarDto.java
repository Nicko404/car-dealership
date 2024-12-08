package ru.clevertec.dto.upsert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertCarDto {

    @NotNull
    @NotBlank
    private String model;

    private String brand;

    @Positive
    private Integer manufactureDate;

    @Positive
    private Integer price;

    @NotNull
    @Positive
    private Long categoryId;

    @NotNull
    @Positive
    private Long carShowroomId;
}
