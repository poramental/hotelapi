package com.bookingApi.bokkingApi.service.repository;

import com.bookingApi.bokkingApi.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {

}
