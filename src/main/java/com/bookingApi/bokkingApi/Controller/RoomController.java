package com.bookingApi.bokkingApi.Controller;


import com.bookingApi.bokkingApi.DTO.RoomDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.models.RoomEntity;
import com.bookingApi.bokkingApi.repository.HotelRepository;
import com.bookingApi.bokkingApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping(params = {"hotel_name","room_number"})
    public RoomEntity getRoom(@RequestParam(name = "hotel_name") String hotelName,@RequestParam(name = "room_number") int roomNumber ){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){

            List<RoomEntity> rooms = opt_hotel.get().getRooms();
            System.out.println(rooms.toString());
            int fIndex = 0;
            int lIndex = rooms.size() - 1;
            while(fIndex <= lIndex) {
                int middleIndex = (fIndex + lIndex) / 2;
                if(rooms.get(middleIndex).getNumber() == roomNumber) return rooms.get(middleIndex);
                else if (rooms.get(middleIndex).getNumber() < roomNumber) fIndex = middleIndex + 1;
                else if (rooms.get(middleIndex).getNumber() > roomNumber) lIndex = middleIndex - 1;
            }
        }return null;
    }

    @PostMapping
    public HttpStatus addRoom(@RequestParam(name = "hotel_name") String hotelName, @RequestBody RoomDto roomDto){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        RoomEntity room = new RoomEntity()
                .setDescription(roomDto.getDescription())
                .setFreeTag(true)
                .setNumber(roomDto.getNumber())
                .setPrice(roomDto.getPrice())
                .setType(roomDto.getType())
                .setHotelId(opt_hotel.get().getId());
        if(opt_hotel.isPresent()){
            roomRepository.save(room);
            return HttpStatus.CREATED;

        }else return HttpStatus.NOT_FOUND;

    }

    @DeleteMapping(params = {"hotel_name","room_number"})
    public HttpStatus deleteRoom(@RequestParam(name = "hotel_name") String hotelName, @RequestParam(name = "room_number") long room_number){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.findByhotelIdAndNumber(opt_hotel.get().getId(), room_number);
            if(opt_room.isPresent()){
                roomRepository.delete(opt_room.get());
                return HttpStatus.OK;
            }
        }
        return HttpStatus.NOT_FOUND;
    }

/* TODO create PATH mapping for this class*/



}
