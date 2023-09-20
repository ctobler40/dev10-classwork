package learn.field_agent.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLSyntaxErrorException;

// Use the @ControllerAdvice annotation to register an exception handler for all controllers.
@ControllerAdvice
public class GlobalErrorHandler
{
    // Catch and handle exceptions at two levels.
    // 1. Determine the most precise exception for data integrity failures and handle it with a specific data integrity message.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handleException(HttpMediaTypeNotSupportedException ex)
    {
        return new ResponseEntity<>("Content-Type application/json required.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // 2. For all other exceptions, create a general "sorry, not sorry" response that doesn't share exception details.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex)
    {
        return new ResponseEntity<>("Sorry, Not Sorry... ;)", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}