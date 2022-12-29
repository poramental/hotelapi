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

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    private final RoomService roomService;


    RoomController(RoomService roomService,
                   HotelRepository hotelRepository,
                   RoomRepository roomRepository){
        this.roomService = roomService;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping()
    public List<RoomEntity> getRooms(@PathVariable("hotel_name") String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()) {
            return opt_hotel.get().getRooms();
        }else{
            return null; //TODO add not found exception for get rooms method;
        }
    }

    @GetMapping( path = "/{room_number}")
    public RoomEntity getRoom(@PathVariable("hotel_name") String hotelName,
                              @PathVariable(name = "room_number") int roomNumber ){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.findByhotelIdAndNumber(opt_hotel.get().getId(),roomNumber);
            if(opt_room.isPresent()){
                return opt_room.get();
            }

        }return null; //TODO add not found exception for get room method;
    }

    @PostMapping()
    public HttpStatus addRoom(@PathVariable("hotel_name") String hotelName,
                              @RequestBody RoomDto roomDto){
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

    @DeleteMapping( path = "/{room_number}")
    public HttpStatus deleteRoom(@PathVariable("hotel_name") String hotelName,
                                 @PathVariable(name = "room_number") int room_number){
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


    @PutMapping()
    public HttpStatus updateRoom(@PathVariable( "hotel_name") String hotelName,
                                 @RequestBody RoomDto roomDto){
            return roomService.update(hotelName,roomDto);
    }

    @GetMapping(params = {"free_rooms"})
    public List<RoomEntity> getAllFreeRooms(@PathVariable("hotel_name") String hotelName,
                                            @RequestParam(name = "free_rooms") boolean freeTag){
        if(freeTag == true) return roomService.getAllFreeRooms(hotelName);
        else return roomService.getAllOccupiedRooms(hotelName);
    }


    @PatchMapping(path = "/{room_number}" , params = "is_free")
    public HttpStatus setRoomFreeTag(@PathVariable("room_number") int roomNumber,
                                      @PathVariable("hotel_name") String hotelName,
                                      @RequestParam("is_free") boolean freeTag){
    return roomService.setRoomFreeTag(hotelName,roomNumber,freeTag);


    }

    @PatchMapping(path = "/{price}")
    public HttpStatus setNewPrice(@PathVariable("room_number") int roomNumber,
                                     @PathVariable("hotel_name") String hotelName,
                                     @RequestParam("price") double price){
        return roomService.setRoomPrice(hotelName,roomNumber,price);


    }




}
