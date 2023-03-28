package com.bookingApi.bookingApi.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity(name = "excursions")
@Data
public class ExcursionEntity {

    @Id
    @Column(updatable = false)
    private UUID id;
    private String description;
    @Column(name="hotel_id")
    private UUID hotelId;

    private String name;

    private double price;
}


