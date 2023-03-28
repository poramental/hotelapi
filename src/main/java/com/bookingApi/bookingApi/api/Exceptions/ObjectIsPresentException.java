package com.bookingApi.bookingApi.api.Exceptions;

public class ObjectIsPresentException extends RuntimeException{
    public ObjectIsPresentException(String message){
        super(message);
    }
}
