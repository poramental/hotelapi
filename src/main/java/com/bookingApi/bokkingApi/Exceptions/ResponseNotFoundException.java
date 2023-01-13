package com.bookingApi.bokkingApi.Exceptions;



public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(String message){
        super(message);
    }
}
