package com.bookingApi.bokkingApi.services;


import com.bookingApi.bokkingApi.DTO.RoomDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.models.RoomEntity;
import com.bookingApi.bokkingApi.repositories.HotelRepository;
import com.bookingApi.bokkingApi.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.List;
@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public HttpStatus update(String hotelName, RoomDto roomDto){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.findByhotelIdAndNumber(opt_hotel.get().getId(),
                                                                                    roomDto.getNumber());
            if(opt_room.isPresent()){
                RoomEntity roomDb = opt_room.get();
                if(Objects.nonNull(roomDto.getDescription()) && !"".equals(roomDto.getDescription())){
                    roomDb.setDescription(roomDto.getDescription());
                }
                if(Objects.nonNull(roomDto.getType()) && !"".equals(roomDto.getType())){
                    roomDb.setType(roomDto.getType());
                }
                if(Objects.nonNull(roomDto.getPrice())){
                    roomDb.setPrice(roomDto.getPrice());
                }
                roomDb.setFreeTag(roomDto.getFreeTag());
                roomRepository.save(roomDb);
                return HttpStatus.OK;
            }

        }return HttpStatus.NOT_FOUND;
    }

    public List<RoomEntity> getAllFreeRooms(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return roomRepository.findByhotelIdAndFreeTagTrue(opt_hotel.get().getId());
        }

        return new ArrayList<>();

    }

    public List<RoomEntity> getAllOccupiedRooms(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return roomRepository.findByhotelIdAndFreeTagFalse(opt_hotel.get().getId());
        }

        return new ArrayList<>();

    }

    public HttpStatus setRoomFreeTag(String hotelName,int roomNumber, boolean freeTag){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.findByhotelIdAndNumber(opt_hotel.get().getId(),roomNumber);
            if(opt_room.isPresent()){
                roomRepository.save(opt_room.get().setFreeTag(freeTag));
                return HttpStatus.OK;
            }

        }
        return HttpStatus.NOT_FOUND;
    }
    public HttpStatus setRoomPrice(String hotelName,int roomNumber, double price){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<RoomEntity> opt_room = roomRepository.findByhotelIdAndNumber(opt_hotel.get().getId(),roomNumber);
            if(opt_room.isPresent()){
                roomRepository.save(opt_room.get().setPrice(price));
                return HttpStatus.OK;
            }

        }
        return HttpStatus.NOT_FOUND;
    }


}