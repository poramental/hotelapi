package com.bookingApi.bokkingApi.services;


import com.bookingApi.bokkingApi.DTO.ExcursionDto;
import com.bookingApi.bokkingApi.Exceptions.ResponseNotFoundException;
import com.bookingApi.bokkingApi.mappers.ExcursionListMapper;
import com.bookingApi.bokkingApi.mappers.ExcursionMapper;
import com.bookingApi.bokkingApi.models.ExcursionEntity;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.repositories.ExcursionRepository;
import com.bookingApi.bokkingApi.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ExcursionService {


    private final HotelRepository hotelRepository;

    private final ExcursionMapper excursionMapper;

    private final ExcursionListMapper excursionListMapper;
    private final ExcursionRepository excursionRepository;

    public ResponseEntity<List<ExcursionDto>> getExcursions(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return new ResponseEntity<List<ExcursionDto>>(excursionListMapper.toDtoList(opt_hotel.get().getExcursions()), HttpStatus.OK);
        }else throw new ResponseNotFoundException("hotel with name : " + hotelName + "Not Found.");
    }


    public HttpStatus addExcursion(String hotelName, ExcursionDto excursionDto){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            ExcursionEntity excursion = excursionMapper.toEntity(excursionDto);
            excursion.setId(UUID.randomUUID());
            excursion.setHotelId(opt_hotel.get().getId());
            excursionRepository.save(excursion);
            return HttpStatus.CREATED;

        }else return HttpStatus.CONFLICT;
    }

}
