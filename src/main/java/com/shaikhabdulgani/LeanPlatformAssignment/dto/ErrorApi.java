package com.shaikhabdulgani.LeanPlatformAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorApi {

    int statusCode;
    String type;
    String message;
    Date date;
}
