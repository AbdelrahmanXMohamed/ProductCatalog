package com.example.productCatalog.excaptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationsExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>(
                getErrorsMap(ex.getBindingResult().getFieldErrors()
                        .stream()
                        .map(FieldError::getField)
                        .collect(Collectors.toList()),
                        ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList())
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> getErrorsMap(List<String> errorsFields,List<String> errorsMessages) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Wrong data provided");
        errorResponse.put("errors", getErrorsDetailsMap(errorsFields,errorsMessages));
        return errorResponse;
    }
    private Map<String, String> getErrorsDetailsMap(List<String> errorsFields,List<String> errorsMessages) {
        Map<String, String> errorResponse = new HashMap<>();
        for (int i =0;i<errorsFields.size();i++)
            errorResponse.put(errorsFields.get(i).substring(errorsFields.get(i).lastIndexOf(".")+1), errorsMessages.get(i));
        return errorResponse;
    }
}
