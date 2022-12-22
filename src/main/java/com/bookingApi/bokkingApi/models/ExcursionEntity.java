package com.bookingApi.bokkingApi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@IdClass(pk_excursionId.class)
@Entity(name = "excursions")
@Data
public class ExcursionEntity {


    private String description;

    @Id
    @Column(name="hotel_id")
    private String hotelId;
    @Id
    private String name;

    private long price;
}

@EqualsAndHashCode
@NoArgsConstructor

class pk_excursionId implements Serializable{
    private String hotelId;
    private String name;
}
