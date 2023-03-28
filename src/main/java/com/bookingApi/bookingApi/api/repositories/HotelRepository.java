package com.bookingApi.bookingApi.api.repositories;

import com.bookingApi.bookingApi.api.models.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
    Optional<HotelEntity> findByName(String name);
    List<HotelEntity> findByAddress(String location);


}
