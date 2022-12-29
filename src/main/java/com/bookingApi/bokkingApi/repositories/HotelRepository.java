package com.bookingApi.bokkingApi.repositories;

import com.bookingApi.bokkingApi.models.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
    Optional<HotelEntity> findByName(String name);
    List<HotelEntity> findByAddress(String location);


}
