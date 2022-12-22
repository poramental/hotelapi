package com.bookingApi.bokkingApi.repository;

import com.bookingApi.bokkingApi.models.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelEntity,Long> {
    Optional<HotelEntity> findByName(String name);
    List<HotelEntity> findByAddress(String location);


}
