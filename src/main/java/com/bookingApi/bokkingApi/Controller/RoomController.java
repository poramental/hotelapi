package com.bookingApi.bokkingApi.Controller;


import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.models.RoomEntity;
import com.bookingApi.bokkingApi.repository.HotelRepository;
import com.bookingApi.bokkingApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("hotels/rooms")
public class RoomController {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @GetMapping
    public List<RoomEntity> getRooms(@RequestParam(name = "hotel_name") String hotelName){
        Optional<HotelEntity> hotel = hotelRepository.findByName(hotelName);
        return hotel.get().getRooms();
    }

//    @PostMapping
//    public HttpStatus addRoom(@RequestParam(name = "hotel_name") String hotelName, @RequestBody RoomDto roomDto){
//        List<HotelEntity>
//    }


}
