package com.bookingApi.bokkingApi.repository;

import com.bookingApi.bokkingApi.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity,Long> {

}
