package com.bookingApi.bokkingApi.Controller;


import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.models.Hotel;
import com.bookingApi.bokkingApi.models.Room;
import com.bookingApi.bokkingApi.service.repository.HotelRepository;
import com.bookingApi.bokkingApi.service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@RestController
@RequestMapping(value = "/hotels")
public class HotelController {


    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public HotelController(HotelRepository hotelRepository, RoomRepository roomRepository){
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }
    @GetMapping
    public Iterable<Hotel> getHotels(){
        return hotelRepository.findAll();
    }



    @PostMapping
    public HttpStatus addHotels(@RequestBody List<HotelDto> hotelDtoList){
        try{
            for(HotelDto hotelDto : hotelDtoList){
                Hotel hotel = new Hotel()
                        .setId(UUID.randomUUID().toString())
                        .setAddress(hotelDto.getAddress())
                        .setType(hotelDto.getType())
                        .setRooms(hotelDto.getRooms())
                        .setExcursions(hotelDto.getExcursions())
                        .setServices(hotelDto.getServices())
                        .setDescription(hotelDto.getDescription())
                        .setName(hotelDto.getName());
                for(Room room : hotelDto.getRooms()){
                    room.setHotel_id(hotel.getId());
                    roomRepository.save(room);
                }
                hotelRepository.save(hotel);


            }

            return HttpStatus.OK;
        }catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.CONFLICT;
        }
    }

    @DeleteMapping
    public HttpStatus deleteHotel(@RequestParam(name = "id") long id){
        Optional<Hotel> opt_hotel = hotelRepository.findById(id);
        if(opt_hotel.isPresent()){
            hotelRepository.delete(opt_hotel.get());
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }

    }

}
