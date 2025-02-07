package com.nextgen.user_management_system.handler;

import com.nextgen.user_management_system.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ProblemDetail handleUserException(UserException ex) {
        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(ex.getHttpStatus(), "Request is invalid");
        problemDetail.setDetail(ex.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult()
                .getFieldErrors();

        Map<String,Object> errorMap = errors
                .stream()
                .collect(Collectors.toMap(x->x.getField(), x->x.getDefaultMessage()));

        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request is invalid");

        problemDetail.setProperties(errorMap);
        return problemDetail;

    }


}
