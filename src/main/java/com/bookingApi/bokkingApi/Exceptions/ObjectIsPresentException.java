package com.bookingApi.bokkingApi.Exceptions;

public class ObjectIsPresentException extends RuntimeException{
    public ObjectIsPresentException(String message){
        super(message);
    }
}
