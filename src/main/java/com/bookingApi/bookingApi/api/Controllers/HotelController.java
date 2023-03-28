package com.bookingApi.bookingApi.api.Controllers;


import com.bookingApi.bookingApi.api.DTO.HotelDto;
import com.bookingApi.bookingApi.api.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;




@RestController
@RequestMapping(value = "rest/hotels")
public class HotelController {
    private final HotelService hotelService;

    HotelController( HotelService hotelService){
        this.hotelService = hotelService;

    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getHotels(){
        return hotelService.getHotels();
    }

    @GetMapping(path = "/{hotel_name}")
    public ResponseEntity<HotelDto> getHotel(@PathVariable("hotel_name") String hotelName) {
        hotelName = hotelName.replace("_", " ");
        return hotelService.getHotel(hotelName);
    }


    @PostMapping
    public HttpStatus addHotels(@RequestBody List<HotelDto> hotelDtoList){
            return hotelService.saveAll(hotelDtoList);

    }


    @DeleteMapping(path = "/{hotel_name}")
    public HttpStatus deleteHotel(@PathVariable("hotel_name") String hotelName){
        hotelName = hotelName.replace("_", " ");
        return hotelService.deleteHotelByName(hotelName);

    }

    @PutMapping
    public HttpStatus updateHotel(@RequestBody HotelDto hotelDto){
        return hotelService.update(hotelDto);
    }

    @DeleteMapping(params = "address")
    public HttpStatus deleteHotelByLocation(@RequestParam("address") String location){
        return hotelService.deleteHotelByLocation(location);
    }

    @PatchMapping("/{hotel_name}")
    public HttpStatus renameHotel(@PathVariable("hotel_name") String hotelName,
                                  @RequestParam(name = "new_hotel_name") String newHotelName ){
        hotelName = hotelName.replace("_", " ");
        return hotelService.renameHotel(hotelName, newHotelName);

    }

}
