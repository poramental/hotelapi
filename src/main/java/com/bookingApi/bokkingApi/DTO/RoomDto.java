package com.bookingApi.bokkingApi.DTO;



import lombok.Data;

@Data
public class RoomDto {

    private String hotel_id;
    private String description;

    private String type;

    private long number;

    private long price;
}
