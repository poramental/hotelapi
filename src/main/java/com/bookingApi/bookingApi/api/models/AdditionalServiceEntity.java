package com.bookingApi.bookingApi.api.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Entity(name = "services")
@Data
public class AdditionalServiceEntity {


    @Id
    @Column(name="service_id")
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<HotelEntity> hotels;

    private String description;


    private String name;

    private double price;


    public AdditionalServiceEntity addHotel(HotelEntity hotel){
        hotels.add(hotel);
        return this;
    }

    public AdditionalServiceEntity removeHotel(HotelEntity hotel){
        hotels.remove(hotel);
        return this;
    }

}

