package com.revature.web.aspects;


import com.revature.dtos.ErrorResponse;
import com.revature.exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class ErrorResponseAspect {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAuthenticationException(AuthenticationException ae){
        return new ErrorResponse(401, ae.getMessage());
    }

}
