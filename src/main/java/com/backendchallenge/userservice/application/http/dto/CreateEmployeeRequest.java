package com.backendchallenge.userservice.application.http.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    @Positive
    @NotNull
    private Long idEmployee;
    @Positive
    @NotNull
    private Long idRestaurant;
}
