package com.bookingApi.bokkingApi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;


@Entity(name = "rooms")
@Data
@Accessors(chain = true)
@IdClass(pk_roomId.class)
public class RoomEntity {

    private String description;
    private String type;
    @Id
    @Column(updatable = false)
    private int number;

    private double price;

    @Id
    @Column(name = "hotel_id")
    private UUID hotelId;

    @Column(name = "free_tag")
    private Boolean freeTag;

}

@NoArgsConstructor
@EqualsAndHashCode
class pk_roomId implements Serializable{
    private UUID hotelId;
    private int number;
}