package com.bookingApi.bokkingApi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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


