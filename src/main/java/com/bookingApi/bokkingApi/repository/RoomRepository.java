package com.bookingApi.bokkingApi.repository;

import com.bookingApi.bokkingApi.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
    Optional<RoomEntity> findByhotelIdAndNumber(String hotel_id, long number);
    List<RoomEntity> findByhotelIdAndFreeTagTrue(String hotel_id);

    List<RoomEntity> findByhotelIdAndFreeTagFalse(String hotel_id);
}
