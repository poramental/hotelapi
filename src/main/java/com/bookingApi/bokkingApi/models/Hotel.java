package com.bookingApi.bokkingApi.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "hotels")
@Data
@Accessors(chain = true)
public class Hotel {
    @Id
    private String id;

    private String type;

    private String description;

    private String name;


    @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL)
    @JoinColumn(name="hotel_id", referencedColumnName = "id")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(targetEntity = HotelService.class, cascade = CascadeType.ALL)
    @JoinColumn(name="hotel_id", referencedColumnName = "id")
    private List<HotelService> services = new ArrayList<>();

    @OneToMany(targetEntity = Excursion.class, cascade = CascadeType.ALL)
    @JoinColumn(name="hotel_id", referencedColumnName = "id")
    private List<Excursion> excursions = new ArrayList<>();

    private String address;

    public Hotel addRooms(List<Room> rooms){
        this.rooms.addAll(rooms);
        return this;
    }

    public Hotel addServices(List<HotelService> services){
        this.services.addAll(services);
        return this;
    }

    public Hotel addExcursions(List<Excursion> excursions){
        this.excursions.addAll(excursions);
        return this;
    }
}
