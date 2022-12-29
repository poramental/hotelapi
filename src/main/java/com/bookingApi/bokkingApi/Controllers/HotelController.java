package com.bookingApi.bokkingApi.Controllers;


import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.repositories.HotelRepository;
import com.bookingApi.bokkingApi.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping(value = "/hotels")
public class HotelController {


    private final HotelRepository hotelRepository;


    private final HotelService hotelService;

    HotelController(HotelRepository hotelRepository, HotelService hotelService){
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;

    }

    @GetMapping
    public Iterable<HotelEntity> getHotels(){
        return hotelRepository.findAll();
    }

    @GetMapping(path = "/{hotel_name}")
    public HotelEntity getHotel(@PathVariable("hotel_name") String hotelName) {
        return hotelService.getHotel(hotelName);
    }


    @PostMapping
    public HttpStatus addHotels(@RequestBody List<HotelDto> hotelDtoList){
        try{
            hotelService.saveAll(hotelDtoList);

            return HttpStatus.CREATED;


        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }


    @DeleteMapping(path = "/{hotel_name}")
    public HttpStatus deleteHotel(@PathVariable("hotel_name") String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            hotelService.delete(opt_hotel.get());
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }

    }

    @PutMapping
    public HttpStatus updateHotel(@RequestBody HotelDto hotelDto){
        try{
            return hotelService.update(hotelDto);
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }

    }

    @DeleteMapping(params = "address")
    public HttpStatus deleteHotelByLocation(@RequestParam("address") String location){
        return hotelService.deleteHotelByLocation(location);
    }

    @PatchMapping("/{hotel_name}")
    public HttpStatus renameHotel(@PathVariable("hotel_name") String hotelName,
                                  @RequestParam(name = "new_hotel_name") String newHotelName ){
        try {
            return hotelService.renameHotel(hotelName, newHotelName);
        }catch (Exception e){
            return HttpStatus.CONFLICT;
        }
    }






}
