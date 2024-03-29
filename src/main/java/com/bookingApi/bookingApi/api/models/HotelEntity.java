package com.bookingApi.bookingApi.api.models;


import jakarta.persistence.*;
import lombok.Data;

import lombok.experimental.Accessors;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity(name = "hotels")
@Data
@Accessors(chain = true)
public class HotelEntity {
    @Id
    @Column(name = "hotel_id")
    private UUID id;

    private String type;

    private String description;

    private String name;


    private String tour_location;

    private String address;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id", updatable = false)
    private List<RoomEntity> rooms = new ArrayList<>();

    @ManyToMany(mappedBy = "hotels", fetch = FetchType.EAGER)
    private List<AdditionalServiceEntity> services = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id", updatable = false)
    private List<ExcursionEntity> excursions = new ArrayList<>();



    public HotelEntity addRooms(List<RoomEntity> rooms){
        this.rooms.addAll(rooms);
        return this;
    }

    public HotelEntity addRoom(RoomEntity room){
        this.rooms.add(room);
        return this;
    }

    public HotelEntity addServices(List<AdditionalServiceEntity> services){
        this.services.addAll(services);
        return this;
    }
    public HotelEntity addService(AdditionalServiceEntity service){
        this.services.add(service);
        return this;
    }

    public HotelEntity addExcursions(List<ExcursionEntity> excursions){
        this.excursions.addAll(excursions);
        return this;
    }

    public HotelEntity removeService(AdditionalServiceEntity service){
        this.services.remove(service);
        return this;
    }
}

