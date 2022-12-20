package com.bookingApi.bokkingApi.Controller;


import com.bookingApi.bokkingApi.models.Hotel;
import com.bookingApi.bokkingApi.models.Room;
import com.bookingApi.bokkingApi.service.repository.HotelRepository;
import com.bookingApi.bokkingApi.service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/hotels")
public class HotelController {


    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    @Autowired
    public HotelController(HotelRepository hotelRepository, RoomRepository roomRepository){

        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }
    @GetMapping
    public Iterable<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    @PostMapping
    public HttpStatus addHotel(@RequestBody Hotel hotel){
        try{
            System.out.println(hotel.toString());
            roomRepository.saveAll(hotel.getRooms());
            hotelRepository.save(hotel);
            return HttpStatus.OK;
        }catch (Exception e) {
            return HttpStatus.CONFLICT;
        }
    }

    @PatchMapping
    public HttpStatus addRoomsInHotel(@RequestParam(name = "id") long id, @RequestBody List<Room> rooms){
        Optional<Hotel> opt_hotel = hotelRepository.findById(id);
        if(opt_hotel.isPresent()){
            Hotel hotel = (Hotel) opt_hotel.get();
            System.out.println(hotel.toString());
            System.out.println(rooms.toString());
            hotel.addRooms(rooms);
            roomRepository.saveAll(rooms);
            hotelRepository.save(hotel);
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }

    }
}
