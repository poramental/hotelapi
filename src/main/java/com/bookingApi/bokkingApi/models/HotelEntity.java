package com.bookingApi.bokkingApi.models;


import jakarta.persistence.*;
import lombok.Data;

import lombok.experimental.Accessors;


import java.util.ArrayList;
import java.util.List;


@Entity(name = "hotels")
@Data
@Accessors(chain = true)
public class HotelEntity {
    @Id
    private String id;

    private String type;

    private String description;

    private String name;


    private String tour_location;

    private String address;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id", updatable=false)
    private List<RoomEntity> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id",updatable=false)
    private List<ServiceEntity> services = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id", updatable=false)
    private List<ExcursionEntity> excursions = new ArrayList<>();



    public HotelEntity addRooms(List<RoomEntity> rooms){
        this.rooms.addAll(rooms);
        return this;
    }

    public HotelEntity addRoom(RoomEntity room){
        this.rooms.add(room);
        return this;
    }

    public HotelEntity addServices(List<ServiceEntity> services){
        this.services.addAll(services);
        return this;
    }

    public HotelEntity addExcursions(List<ExcursionEntity> excursions){
        this.excursions.addAll(excursions);
        return this;
    }
}

