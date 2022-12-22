package com.bookingApi.bokkingApi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity(name = "excursions")
@Data
public class ExcursionEntity {

    @Id
    private String id;

    private String description;

    private String hotel_id;
    private String name;

    private long price;
}


