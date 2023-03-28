package com.bookingApi.bookingApi.api.services;


import com.bookingApi.bookingApi.api.DTO.HotelDto;
import com.bookingApi.bookingApi.api.Exceptions.ObjectIsPresentException;
import com.bookingApi.bookingApi.api.Exceptions.ResponseNotFoundException;
import com.bookingApi.bookingApi.api.mappers.*;
import com.bookingApi.bookingApi.api.models.HotelEntity;
import com.bookingApi.bookingApi.api.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class HotelService {


    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    private final HotelListMapper hotelListMapper;

    private final RoomListMapper roomListMapper;

    private final AdditionalServiceListMapper serviceListMapper;

    private final ExcursionListMapper excursionListMapper;


    public ResponseEntity<List<HotelDto>> getHotels(){
        return new ResponseEntity<List<HotelDto>>(hotelListMapper.toDtoList(hotelRepository.findAll()),HttpStatus.OK);
    }


    public HttpStatus saveAll(List<HotelDto> hotelDtoList) {

        List<HotelEntity> hotels = hotelListMapper.toEntityList(hotelDtoList);
        hotels.forEach(

                hotel -> {
                    if(hotelRepository.findByName(hotel.getName()).isPresent())
                        throw new ObjectIsPresentException("hotel with name '"+hotel.getName()+"' is already exist");
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
                                    service.addHotel(hotel);
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
        return HttpStatus.CREATED;

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
                        service.addHotel(hotel);
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
        } else throw new ResponseNotFoundException("hotel '" + hotelDto.getName() + "' not found");
    }

    public HttpStatus renameHotel(String hotelName, String newHotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(hotelRepository.findByName(newHotelName).isPresent()) throw new ObjectIsPresentException("this hotel already exists");
        if(opt_hotel.isPresent()){
            hotelRepository.save(opt_hotel.get().setName(newHotelName));
            return HttpStatus.OK;
        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");
    }


    public ResponseEntity<HotelDto> getHotel(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return new ResponseEntity<HotelDto>(hotelMapper.toDto(opt_hotel.get()),HttpStatus.OK);
        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");
    }

    public HttpStatus deleteHotelByLocation(String location){
        List<HotelEntity> hotels = hotelRepository.findByAddress(location);
        if(hotels.isEmpty()){
            throw new ResponseNotFoundException("no hotels with address : " + location + ".");
        }else{
            deleteAll(hotels);
            return HttpStatus.OK;
        }
    }

    public HttpStatus deleteHotelByName(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            delete(opt_hotel.get());
            return HttpStatus.OK;
        }else throw new ResponseNotFoundException("hotel '" + hotelName + "' not found.");
    }




}