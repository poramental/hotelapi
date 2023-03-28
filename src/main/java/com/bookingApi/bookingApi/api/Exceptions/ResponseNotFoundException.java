package com.bookingApi.bookingApi.api.Exceptions;



public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(String message){
        super(message);
    }
}
