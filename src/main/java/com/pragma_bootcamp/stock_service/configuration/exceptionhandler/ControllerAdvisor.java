package com.pragma_bootcamp.stock_service.configuration.exceptionhandler;

import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.exception.DuplicatedEntryException;
import com.pragma_bootcamp.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_bootcamp.stock_service.configuration.Constants;
import com.pragma_bootcamp.stock_service.domain.exception.EmptyFieldException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(String.format(exception.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException(NoDataFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(String.format(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, exception.getMessage()), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(String.format(exception.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(DuplicatedEntryException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedEntryException(DuplicatedEntryException exception) {
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(String.format(Constants.DUPLICATE_CATEGORY_EXCEPTION_MESSAGE),
                        HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleCharacterLengthException(ConstraintViolationException exception){
        Map<String, String> errors = new HashMap<>();

        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        String formattedMessage = errors.entrySet().stream()
                .map(entry -> String.format("Field '%s': %s", entry.getKey(), entry.getValue()))
                .reduce((message1, message2) -> message1 + "; " + message2)
                .orElse("Validation error");
        ExceptionResponse response = new ExceptionResponse(formattedMessage, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
}
