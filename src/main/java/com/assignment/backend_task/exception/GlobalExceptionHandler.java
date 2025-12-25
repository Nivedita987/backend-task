package com.assignment.backend_task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentDeletionException.class)
    public ResponseEntity<ErrorResponse> handleStudentDeletionException(
            StudentDeletionException ex) {

        ErrorResponse error = new ErrorResponse(
                400,
                "BAD_REQUEST",
                ex.getMessage()
        );

        return ResponseEntity.badRequest().body(error);
    }

}
