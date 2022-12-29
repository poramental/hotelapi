package com.bookingApi.bokkingApi.services;


import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.mappers.*;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.repositories.HotelRepository;
import com.bookingApi.bokkingApi.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class HotelService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    private final HotelListMapper hotelListMapper;

    private final RoomListMapper roomListMapper;

    private final AdditionalServiceListMapper serviceListMapper;

    private final ExcursionListMapper excursionListMapper;


    public void saveAll(List<HotelDto> hotelDtoList) {

        List<HotelEntity> hotels = hotelListMapper.toEntityList(hotelDtoList);
        hotels.forEach(
                hotel -> {
                    hotel.setId(UUID.randomUUID());
                    if(hotel.getRooms() != null)
                    hotel.getRooms().forEach(
                            room -> {
                                if(room != null)
                                    room.setHotelId(hotel.getId());
                            }
                    );
                    if(hotel.getServices() != null)
                    hotel.getServices().forEach(
                            service ->{
                                if(service != null )
                                    service.setHotelId(hotel.getId());
                            }
                    );
                    if(hotel.getExcursions() != null)
                    hotel.getExcursions().forEach(
                            excursion -> {
                                if(excursion != null)
                                    excursion.setHotelId(hotel.getId());
                            }
                    );
                }
        );
        hotelRepository.saveAll(hotels);

    }
    public void save(HotelDto hotelDto){
        HotelEntity hotel = hotelMapper.toEntity(hotelDto);
        hotel.setId(UUID.randomUUID());
        if(hotel.getRooms() != null)
        hotel.getRooms().forEach(
                room -> {
                    if(room != null)
                        room.setHotelId(hotel.getId());
                }
        );
        if(hotel.getServices() != null)
        hotel.getServices().forEach(
                service ->{
                    if(service != null )
                        service.setHotelId(hotel.getId());
                }
        );
        if(hotel.getExcursions() != null)
        hotel.getExcursions().forEach(
                excursion -> {
                    if(excursion != null)
                        excursion.setHotelId(hotel.getId());
                }
        );
        hotelRepository.save(hotel);
    }





    public void deleteAll(List<HotelEntity> hotels){
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
                hotelDb.setRooms(roomListMapper.toEntityList(hotelDto.getRooms()));
            }
            if(Objects.nonNull(hotelDto.getExcursions()) ){
                hotelDb.setExcursions(excursionListMapper.toEntityList(hotelDto.getExcursions()));
            }
            if(Objects.nonNull(hotelDto.getServices())){
                hotelDb.setServices(serviceListMapper.toEntityList(hotelDto.getServices()));
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


    public HotelEntity getHotel(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return opt_hotel.get();
        }else{
            return null; //TODO add not found exception for get Hotel method;
        }
    }

    public HttpStatus deleteHotelByLocation(String location){
        List<HotelEntity> hotels = hotelRepository.findByAddress(location);
        if(hotels.isEmpty()){
            return HttpStatus.NOT_FOUND;
        }else{

            deleteAll(hotels);
            return HttpStatus.OK;
        }
    }


}