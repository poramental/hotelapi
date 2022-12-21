package com.bookingApi.bokkingApi.service.repository;

import com.bookingApi.bokkingApi.models.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServiceRepository extends JpaRepository<HotelService, Long> {
}
