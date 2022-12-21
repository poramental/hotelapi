package com.bookingApi.bokkingApi.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity(name = "services")
@IdClass(pk_services.class)
public class HotelService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Id
    private String hotel_id;

    private String description;


    private long price;


}

@EqualsAndHashCode
@NoArgsConstructor
class pk_services implements Serializable{
    private String id;
    private String hotel_id;
}