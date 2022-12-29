package com.bookingApi.bokkingApi.Controllers;


import com.bookingApi.bokkingApi.DTO.RoomDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.models.RoomEntity;
import com.bookingApi.bokkingApi.repositories.HotelRepository;
import com.bookingApi.bokkingApi.repositories.RoomRepository;
import com.bookingApi.bokkingApi.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping(path = "hotels/{hotel_name}/rooms")
public class RoomController {

    private final RoomService roomService;


    RoomController(RoomService roomService){
        this.roomService = roomService;

    }

    @GetMapping()
    public List<RoomDto> getRooms(@PathVariable("hotel_name") String hotelName){
        hotelName = hotelName.replace("_", " ");
        return roomService.getRooms(hotelName);
    }

    @GetMapping( path = "/{room_number}")
    public RoomDto getRoom(@PathVariable("hotel_name") String hotelName,
                              @PathVariable(name = "room_number") int roomNumber ){
        hotelName = hotelName.replace("_", " ");
        return roomService.getRoom(hotelName,roomNumber);
    }

    @PostMapping()
    public HttpStatus addRoom(@PathVariable("hotel_name") String hotelName,
                              @RequestBody RoomDto roomDto){
        hotelName = hotelName.replace("_", " ");
        return roomService.addRoom(hotelName,roomDto);


    }

    @DeleteMapping( path = "/{room_number}")
    public HttpStatus deleteRoom(@PathVariable("hotel_name") String hotelName,
                                 @PathVariable(name = "room_number") int number){
        hotelName = hotelName.replace("_", " ");
        return roomService.deleteRoom(hotelName, number);
    }


    @PutMapping()
    public HttpStatus updateRoom(@PathVariable( "hotel_name") String hotelName,
                                 @RequestBody RoomDto roomDto){
        hotelName = hotelName.replace("_", " ");
        return roomService.update(hotelName,roomDto);
    }

    @GetMapping(params = {"free_rooms"})
    public List<RoomDto> getAllFreeRooms(@PathVariable("hotel_name") String hotelName,
                                            @RequestParam(name = "free_rooms") boolean freeTag){
        hotelName = hotelName.replace("_", " ");
        if(freeTag == true) return roomService.getAllFreeRooms(hotelName);
        else return roomService.getAllOccupiedRooms(hotelName);
    }


    @PatchMapping(path = "/{room_number}" , params = "is_free")
    public HttpStatus setRoomFreeTag(@PathVariable("room_number") int roomNumber,
                                      @PathVariable("hotel_name") String hotelName,
                                      @RequestParam("is_free") boolean freeTag){
        hotelName = hotelName.replace("_", " ");
        return roomService.setRoomFreeTag(hotelName,roomNumber,freeTag);


    }

    @PatchMapping(path = "/{price}")
    public HttpStatus setNewPrice(@PathVariable("room_number") int roomNumber,
                                     @PathVariable("hotel_name") String hotelName,
                                     @RequestParam("price") double price){
        hotelName = hotelName.replace("_", " ");
        return roomService.setRoomPrice(hotelName,roomNumber,price);


    }




}
