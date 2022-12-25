package com.bookingApi.bokkingApi.service;


import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.repository.HotelRepository;
import com.bookingApi.bokkingApi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class HotelService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public void saveAll(List<HotelDto> hotelDtoList) {


        hotelDtoList.forEach(
                hotelDto -> {
                    HotelEntity hotelEntity = new HotelEntity();
                    hotelEntity.setId(UUID.randomUUID().toString())
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
                                room.setHotelId(hotelEntity.getId());
                                roomRepository.save(room);

                            }
                    );


                    hotelRepository.save(hotelEntity);

                }
        );

    }
    public void save(HotelDto hotelDto){
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setId(UUID.randomUUID().toString())
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


                    room.setHotelId(hotelEntity.getId());
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

    public HttpStatus update(HotelDto hotelDto){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelDto.getName());
        if(opt_hotel.isPresent()){
            HotelEntity hotelDb = opt_hotel.get();
            if(Objects.nonNull(hotelDto.getAddress()) && !"".equals(hotelDto.getAddress())){
                hotelDb.setAddress(hotelDto.getAddress());
            }
            if(Objects.nonNull(hotelDto.getName()) && !"".equals(hotelDto.getName())){
                hotelDb.setName(hotelDto.getName());
            }
            if(Objects.nonNull(hotelDto.getType()) && !"".equals(hotelDto.getType())){
                hotelDb.setType(hotelDto.getType());
            }
            if(Objects.nonNull(hotelDto.getTour_location()) && !"".equals(hotelDto.getTour_location())){
                hotelDb.setTour_location(hotelDto.getTour_location());
            }
            if(Objects.nonNull(hotelDto.getDescription()) && !"".equals(hotelDto.getDescription())){
                hotelDb.setDescription(hotelDto.getDescription());
            }
            if(Objects.nonNull(hotelDto.getRooms())){
                hotelDb.setRooms(hotelDto.getRooms());
            }
            if(Objects.nonNull(hotelDto.getServices()) ){
                hotelDb.setServices(hotelDto.getServices());
            }
            if(Objects.nonNull(hotelDto.getExcursions())){
                hotelDb.setExcursions(hotelDto.getExcursions());
            }

            hotelRepository.save(hotelDb);
            return HttpStatus.OK;
        }else{
            return HttpStatus.NOT_FOUND;
        }


    }

    public HttpStatus renameHotel(String hotelName, String newHotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            hotelRepository.save(opt_hotel.get().setName(newHotelName));
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }



}