package com.bookingApi.bokkingApi.DTO;


import com.bookingApi.bokkingApi.models.ExcursionEntity;
import com.bookingApi.bokkingApi.models.ServiceEntity;
import com.bookingApi.bokkingApi.models.RoomEntity;
import com.bookingApi.bokkingApi.models.ServiceEntity;
import lombok.Data;


import java.util.List;

@Data
public class HotelDto {

    private String description;
    private String type;
    private List<RoomEntity> rooms;

    private  List<ServiceEntity> services;

    private List<ExcursionEntity> excursions;

    private String address;

    private String name;

    private String tour_location;


}
