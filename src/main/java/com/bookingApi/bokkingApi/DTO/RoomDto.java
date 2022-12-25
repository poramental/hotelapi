package com.bookingApi.bokkingApi.DTO;



import lombok.Data;
import lombok.Getter;

@Data
public class RoomDto {

    private String hotel_id;
    private String description;

    private String type;

    private boolean freeTag;

    private int number;

    private double price;

    public boolean getFreeTag(){
        return this.freeTag;
    }

}
