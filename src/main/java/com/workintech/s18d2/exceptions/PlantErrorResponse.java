package com.workintech.s18d2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class PlantErrorResponse {
    private String message;
}