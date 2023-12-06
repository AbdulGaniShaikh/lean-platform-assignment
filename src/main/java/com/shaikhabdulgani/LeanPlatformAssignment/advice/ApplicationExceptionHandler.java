package com.shaikhabdulgani.LeanPlatformAssignment.advice;

import com.shaikhabdulgani.LeanPlatformAssignment.dto.ErrorApi;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.InvalidParams;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.NotFound;
import com.shaikhabdulgani.LeanPlatformAssignment.exceptions.UnauthorizedAccess;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach( error ->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorApi handleNotFound(NotFound ex){
        ErrorApi errorApi = new ErrorApi();
        errorApi.setDate(new Date());
        errorApi.setType("NOT FOUND");
        errorApi.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorApi.setMessage(ex.getMessage());

        return errorApi;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParams.class)
    public ErrorApi handleInvalidParams(InvalidParams ex){
        ErrorApi errorApi = new ErrorApi();
        errorApi.setDate(new Date());
        errorApi.setType("BAD REQUEST");
        errorApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorApi.setMessage(ex.getMessage());

        return errorApi;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedAccess.class)
    public ErrorApi handleUnauthorizedAccess(UnauthorizedAccess ex){
        ErrorApi errorApi = new ErrorApi();
        errorApi.setDate(new Date());
        errorApi.setType("UNAUTHORIZED");
        errorApi.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorApi.setMessage(ex.getMessage());

        return errorApi;
    }

}
