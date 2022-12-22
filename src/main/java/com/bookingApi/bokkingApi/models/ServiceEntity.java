package com.bookingApi.bokkingApi.models;


import jakarta.persistence.*;
import lombok.Data;



@Entity(name = "services")
@Data
public class ServiceEntity {
    @Id
    private String id;
    private String hotel_id;

    private String description;

    private String name;

    private long price;


}

