package com.shaikhabdulgani.LeanPlatformAssignment.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RateDAO {

    @NotNull(message = "userId cannot be null")
    @NotEmpty(message = "userId cannot be empty")
    private String userId;

    @Min(value = 1,message = "minimum value of rating should be 1")
    @Max(value = 5,message = "maximum value of rating should be 5")
    private int rating;

}
