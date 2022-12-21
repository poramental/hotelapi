package com.bookingApi.bokkingApi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "excursions")
@Data
@IdClass(pk_excursionId.class)
public class Excursion {

    @Id

    private String id;
    @Id
    private String hotel_id;

    private String description;


    private String name;

    private long price;
}


@NoArgsConstructor
@EqualsAndHashCode
class pk_excursionId implements Serializable{
    private long id;
    private String hotel_id;
}