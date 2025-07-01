package com.gl.bms.utility;

import com.gl.bms.exception.BankManagementSystemException;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerException {

    private static final Log LOGGER = LogFactory.getLog(ControllerException.class);


    @ExceptionHandler(BankManagementSystemException.class)
    public ResponseEntity<Error> customException(BankManagementSystemException ex) {
        Error error = new Error();
        error.setMessage(Collections.singletonList(ex.getMessage()));
        error.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    private static final Map<String, String> CONSTRAINT_VIOLATION_MESSAGES = Map.of(
            "uk4ugnrda1j4104uxydd2llrtb", "Branch contact number already exists.",
            "uk66gkcp94endmotfwb8r4ocxm9", "Account number already exists."
    );

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String rootMsg = ex.getMostSpecificCause().getMessage();

        for (var entry : CONSTRAINT_VIOLATION_MESSAGES.entrySet()) {
            if (rootMsg.contains(entry.getKey())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(entry.getValue());
            }
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate entry detected: " + rootMsg);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> generalException(Exception ex) {
        Error error = new Error();
        error.setMessage(Collections.singletonList("Internal Server Error: " + ex.getMessage()));
        error.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> validationException(MethodArgumentNotValidException ex) {
        Error error = new Error();

        List<String> errMessages = new ArrayList<>();

        errMessages.addAll(ex.getBindingResult().getAllErrors().stream()
                .map(e -> "RequestBody : " + e.getDefaultMessage())
                .collect(Collectors.toList()));

        error.setMessage(errMessages);
        error.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTimeStamp(LocalDateTime.now());
        LOGGER.error(ex);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> validationException(ConstraintViolationException ex) {
        Error error = new Error();

        List<String> errMessages = new ArrayList<>();

        errMessages.addAll(ex.getConstraintViolations().stream()
                .map(e -> "PathVariable : " + e.getMessage())
                .collect(Collectors.toList()));

        error.setMessage(errMessages);
        error.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTimeStamp(LocalDateTime.now());
        LOGGER.error(ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
