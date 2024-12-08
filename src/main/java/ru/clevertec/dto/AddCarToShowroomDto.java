package ru.clevertec.dto;

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
public class AddCarToShowroomDto {

    @Positive
    @NotNull
    private Long carId;

    @Positive
    @NotNull
    private Long showroomId;
}
