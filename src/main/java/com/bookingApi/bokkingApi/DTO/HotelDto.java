package com.bookingApi.bokkingApi.DTO;


import com.bookingApi.bokkingApi.models.Excursion;
import com.bookingApi.bokkingApi.models.HotelService;
import com.bookingApi.bokkingApi.models.Room;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HotelDto {

    private String description;
    private String type;
    private List<Room> rooms;

    private  List<HotelService> services;

    private List<Excursion> excursions;

    private String address;

    private String name;




}
