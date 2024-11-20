package com.in.sp.exception;

import com.in.sp.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandleException {

@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(
            ResourceNotFoundException e,
            WebRequest request

            ) {

    ErrorDetails errorDetails = new ErrorDetails(

            e.getMessage(),
            new Date(),
            request.getDescription(true)
    );

    return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);

}
}
