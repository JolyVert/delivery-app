package com.jolyvert.deliveryapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException loginException, WebRequest webRequest){
        MyErrorDetails myErrorDetails = new MyErrorDetails();
        myErrorDetails.setTimeStamp(LocalDateTime.now());
        myErrorDetails.setMessage(loginException.getMessage());
        myErrorDetails.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException customerException, WebRequest webRequest){
        MyErrorDetails myErrorDetails = new MyErrorDetails();
        myErrorDetails.setTimeStamp(LocalDateTime.now());
        myErrorDetails.setMessage(customerException.getMessage());
        myErrorDetails.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FoodCartException.class)
    public ResponseEntity<MyErrorDetails> foodCartExceptionHandler(FoodCartException foodCartException, WebRequest webRequest){
        MyErrorDetails myErrorDetails = new MyErrorDetails();
        myErrorDetails.setTimeStamp(LocalDateTime.now());
        myErrorDetails.setMessage(foodCartException.getMessage());
        myErrorDetails.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<MyErrorDetails> restaurantExceptionHandler(RestaurantException restaurantException, WebRequest webRequest){
        MyErrorDetails myErrorDetails = new MyErrorDetails();
        myErrorDetails.setTimeStamp(LocalDateTime.now());
        myErrorDetails.setMessage(restaurantException.getMessage());
        myErrorDetails.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

}
