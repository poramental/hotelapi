package com.bookingApi.bookingApi.api.DTO;


import lombok.Data;


import java.util.List;

@Data
public class HotelDto {

    private String description;
    private String type;
    private List<RoomDto> rooms;

    private  List<AdditionalServiceDto> services;

    private List<ExcursionDto> excursions;

    private String address;

    private String name;

    private String tour_location;


}
