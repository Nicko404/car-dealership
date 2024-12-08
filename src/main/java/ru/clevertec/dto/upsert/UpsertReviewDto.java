package ru.clevertec.dto.upsert;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertReviewDto {


    private String content;

    @Positive
    @Range(min = 1, max = 10)
    private Integer rating;

    @Positive
    @NotNull
    private Long clientId;

    @Positive
    @NotNull
    private Long carId;
}
