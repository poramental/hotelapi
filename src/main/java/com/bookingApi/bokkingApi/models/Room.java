package com.bookingApi.bokkingApi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "rooms")
@Data
@IdClass(value = pk_roomId.class)
public class Room {

    @Id
    private long hotel_id;

    private String description;

    private String type;

    @Id
    private long number;

    private long price;


}

@EqualsAndHashCode
@NoArgsConstructor
class pk_roomId implements Serializable {
    private long hotel_id;
    private Long number;

}