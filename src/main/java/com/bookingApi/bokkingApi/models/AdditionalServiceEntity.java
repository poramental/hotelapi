package com.bookingApi.bokkingApi.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity(name = "services")
@Data
public class AdditionalServiceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id")
    private UUID hotelId;

    private String description;


    private String name;

    private double price;


}

