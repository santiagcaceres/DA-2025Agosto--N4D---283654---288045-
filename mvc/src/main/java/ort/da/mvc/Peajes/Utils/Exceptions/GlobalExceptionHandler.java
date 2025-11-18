package ort.da.mvc.Peajes.Utils.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice

public class GlobalExceptionHandler {
    private final int errorCodeStatus = 299; 

    @ExceptionHandler(PuestoException.class)
    public ResponseEntity<String> manejarException(PuestoException ex) {
       return ResponseEntity.status(errorCodeStatus).body(ex.getMessage());
    }
}
