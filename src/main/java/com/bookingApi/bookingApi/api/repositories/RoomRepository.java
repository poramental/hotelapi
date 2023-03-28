package com.bookingApi.bookingApi.api.repositories;

import com.bookingApi.bookingApi.api.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<RoomEntity,UUID> {
    Optional<RoomEntity> findByhotelIdAndNumber(UUID hotel_id, long number);
    List<RoomEntity> findByhotelIdAndFreeTagTrue(UUID hotel_id);
    List<RoomEntity> findByhotelIdAndFreeTagFalse(UUID hotel_id);
}
