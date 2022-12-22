package com.bookingApi.bokkingApi.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@IdClass(pk_serviceId.class)
@Entity(name = "services")
@Data
public class ServiceEntity {

    @Id
    @Column(name = "hotel_id")
    private String hotelId;

    private String description;

    @Id
    private String name;

    private double price;


}

@EqualsAndHashCode
@NoArgsConstructor
class pk_serviceId implements Serializable{
    private String hotelId;
    private String name;
}

