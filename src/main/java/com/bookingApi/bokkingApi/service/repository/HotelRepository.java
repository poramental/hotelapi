package com.bookingApi.bokkingApi.service.repository;

import com.bookingApi.bokkingApi.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
