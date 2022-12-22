package com.bookingApi.bokkingApi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Entity(name = "rooms")
@Data
@Accessors(chain = true)
public class RoomEntity {

    @Id
    private String id;

    private String description;

    private String type;


    private long number;

    private long price;

    private String hotel_id;

    @Column(name = "free_tag")
    private Boolean freeTag;

}

