package com.bookingApi.bokkingApi.repository;

import com.bookingApi.bokkingApi.models.ServiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelServiceRepository extends JpaRepository<ServiceEntity, Long> {

}
