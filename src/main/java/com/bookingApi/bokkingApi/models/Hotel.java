package com.bookingApi.bokkingApi.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "hotels")
@Data
@Accessors(chain = true)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    private String description;

    @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(targetEntity = HotelService.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private List<HotelService> services = new ArrayList<>();

    @OneToMany(targetEntity = Excursion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private List<Excursion> excursions = new ArrayList<>();

    private String address;

    public Hotel addRooms(List<Room> rooms){
        rooms.addAll(rooms);
        return this;
    }

    public Hotel addServices(List<HotelService> services){
        services.addAll(services);
        return this;
    }

}
