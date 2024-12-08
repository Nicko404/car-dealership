package ru.clevertec.dto.upsert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpsertCarShowroomDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String address;
}
