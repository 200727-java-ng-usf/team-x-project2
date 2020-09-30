package com.revature.web.aspects;


import com.revature.dtos.ErrorResponse;
import com.revature.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class ErrorResponseAspect {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthenticationException(AuthenticationException ae){
        return new ErrorResponse(401, ae.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleFailedTransactionException(FailedTransactionException fte){
        return new ErrorResponse(500, fte.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestException(InvalidRequestException ire){
        return new ErrorResponse(400, ire.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException (ResourceNotFoundException rnfe){
        return new ErrorResponse(404, rnfe.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.GONE)
    public ErrorResponse handleGoneException (GoneException ge) {
        return new ErrorResponse(410, ge.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleResourceAlreadySavedException (ResourceAlreadySavedException rase){
        return new ErrorResponse(409, rase.getMessage());
    }
}
