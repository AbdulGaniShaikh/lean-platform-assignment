package com.shaikhabdulgani.LeanPlatformAssignment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewDAO {

    @NotNull(message = "userId cannot be null")
    @NotEmpty(message = "userId cannot be empty")
    private String userId;

    @NotNull(message = "reviewText cannot be null")
    @NotEmpty(message = "reviewText cannot be empty")
    private String reviewText;

}
