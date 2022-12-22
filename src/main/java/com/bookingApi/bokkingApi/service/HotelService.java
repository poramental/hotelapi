package com.bookingApi.bokkingApi.service;


import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.repository.HotelRepository;
import com.bookingApi.bokkingApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class HotelService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public void saveAll(List<HotelDto> hotelDtoList) {


        hotelDtoList.forEach(
                hotelDto -> {
                    HotelEntity hotelEntity = new HotelEntity();
                    hotelEntity
                            .setId(UUID.randomUUID().toString())
                            .setAddress(hotelDto.getAddress())
                            .setType(hotelDto.getType())
                            .setRooms(hotelDto.getRooms())
                            .setExcursions(hotelDto.getExcursions())
                            .setServices(hotelDto.getServices())
                            .setDescription(hotelDto.getDescription())
                            .setName(hotelDto.getName())
                            .setTour_location(hotelDto.getTour_location());
                    hotelDto.getRooms().forEach(
                            room -> {
                                room.setFreeTag(true);
                                room.setId(UUID.randomUUID().toString())
                                        .setHotel_id(hotelEntity.getId());
                                roomRepository.save(room);

                            }
                    );


                    hotelRepository.save(hotelEntity);

                }
        );

    }
    public void save(HotelDto hotelDto){
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity
                .setId(UUID.randomUUID().toString())
                .setAddress(hotelDto.getAddress())
                .setType(hotelDto.getType())
                .setRooms(hotelDto.getRooms())
                .setExcursions(hotelDto.getExcursions())
                .setServices(hotelDto.getServices())
                .setDescription(hotelDto.getDescription())
                .setName(hotelDto.getName())
                .setTour_location(hotelDto.getTour_location());

        hotelDto.getRooms().forEach(
                room -> {

                    room.setId(hotelEntity.getId());

                    roomRepository.save(room);
                }
        );


        hotelRepository.save(hotelEntity);

    }

    public void deleteAll(List<HotelEntity> hotels){
        hotels.forEach(
                hotel ->{
                    roomRepository.deleteAll(hotel.getRooms());
                }
        );
        hotelRepository.deleteAll(hotels);
    }

    public void delete(HotelEntity hotel){


        hotelRepository.delete(hotel);
    }

}