package com.shaikhabdulgani.LeanPlatformAssignment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecommendDAO {

    @NotNull(message = "mentorId cannot be null")
    @NotEmpty(message = "mentorId cannot be empty")
    private String mentorId;

}
