package org.shweta.LibraryManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception
@ControllerAdvice
public class ControllerAdviceExceptionHandler {
//when you annotate a class with @ControllerAdvice annotation,
    //then any exception occured which has calling method in any controller within controllers package
    //would come to this @ControllerAdvice annotate class


    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity<Object> handle(TransactionException transactionException){
    //whenever Exception of type TransactionException come to you handler method will get called
        return new ResponseEntity<>(transactionException.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(exception.getBindingResult().getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
