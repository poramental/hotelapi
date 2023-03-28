package com.bookingApi.bookingApi.api.DTO;



import lombok.Data;

@Data
public class RoomDto {


    private String description;

    private String type;

    private boolean freeTag;


    private int number;

    private double price;

    public boolean getFreeTag(){
        return this.freeTag;
    }

}
