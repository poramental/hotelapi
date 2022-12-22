package com.bookingApi.bokkingApi.Controller;


import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.repository.HotelRepository;
import com.bookingApi.bokkingApi.repository.RoomRepository;
import com.bookingApi.bokkingApi.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;



@RestController
@RequestMapping(value = "/hotels")
public class HotelController {


    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    private final HotelService hotelService;

    HotelController(HotelRepository hotelRepository, RoomRepository roomRepository, HotelService hotelService){
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public Iterable<HotelEntity> getHotels(){
        return hotelRepository.findAll();
    }

    @GetMapping(params = "hotel_name")
    public HotelEntity getHotel(@RequestParam(name = "hotel_name") String hotelName) {
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return opt_hotel.get();
        }else{
           return null;
        }
    }


    @PostMapping
    public HttpStatus addHotels(@RequestBody List<HotelDto> hotelDtoList){
        try{
            System.out.println(hotelDtoList.toString());
            hotelService.saveAll(hotelDtoList);
            return HttpStatus.OK;

        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }
/* TODO create method to adding one hotel
    @PostMapping(params = "type")
    public HttpStatus addHotel(@RequestParam(name = "type") String type, @RequestBody HotelDto hotelDto){

            try {
                hotelService.save(hotelDto);
                return HttpStatus.OK;
            }catch (Exception e){
                e.printStackTrace();
                return HttpStatus.BAD_REQUEST;
            }

    }*/

    @DeleteMapping(params = "hotel_name")
    public HttpStatus deleteHotel(@RequestParam(name = "hotel_name") String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            System.out.println(opt_hotel.get().toString() + "!!!!!!!!!!!!!");
            hotelService.delete(opt_hotel.get());
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }

    }

    @DeleteMapping(params = "address")
    public HttpStatus deleteHotelByLocation(@RequestParam("address") String location){
        List<HotelEntity> hotels = hotelRepository.findByAddress(location);
        if(hotels.isEmpty()){
            return HttpStatus.NOT_FOUND;
        }else{

            hotelService.deleteAll(hotels);
            return HttpStatus.OK;
        }


    }

}
