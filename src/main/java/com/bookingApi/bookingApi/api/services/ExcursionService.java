package com.bookingApi.bookingApi.api.services;


import com.bookingApi.bookingApi.api.DTO.ExcursionDto;
import com.bookingApi.bookingApi.api.Exceptions.ResponseNotFoundException;
import com.bookingApi.bookingApi.api.models.ExcursionEntity;
import com.bookingApi.bookingApi.api.models.HotelEntity;
import com.bookingApi.bookingApi.api.mappers.ExcursionListMapper;
import com.bookingApi.bookingApi.api.mappers.ExcursionMapper;
import com.bookingApi.bookingApi.api.repositories.ExcursionRepository;
import com.bookingApi.bookingApi.api.repositories.HotelRepository;
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
            excursion.setName(excursionDto.getName().replace(" ","_"));
            excursion.setHotelId(opt_hotel.get().getId());
            excursionRepository.save(excursion);
            return HttpStatus.CREATED;
        }else throw new ResponseNotFoundException("hotel with name '" + hotelName +"' not found.");
    }

    public ResponseEntity<ExcursionDto> getExcursionByName(String excursionName){
        Optional<ExcursionEntity> opt_excursion = excursionRepository.findByName(excursionName);
        if(opt_excursion.isPresent())
            return new ResponseEntity<>(excursionMapper.toDto(opt_excursion.get()),HttpStatus.OK);
        else
            throw new ResponseNotFoundException("excursion with name " + excursionName + "not found.");
    }


    public HttpStatus updateExcursion(String excursionName, ExcursionDto excursionDto){
        Optional<ExcursionEntity> opt_excursion = excursionRepository.findByName(excursionName);
        if(opt_excursion.isPresent()){
            ExcursionEntity excursion = opt_excursion.get();
            if(!excursionDto.getDescription().equals("") && excursionDto.getDescription() != null){
                excursion.setDescription(excursionDto.getDescription());
            }
            if(excursionDto.getPrice() != 0.0){
                excursion.setPrice(excursionDto.getPrice());
            }
            if(!excursionDto.getName().equals("") && excursionDto.getName() !=null ){
                excursion.setName(excursionDto.getName());
            }
            return HttpStatus.OK;
        }else
            throw new ResponseNotFoundException("excursion with name '" + excursionName + "' not found.");
    }


    public HttpStatus deleteExcursionByName(String excursionName){
        Optional<ExcursionEntity> opt_excursion = excursionRepository.findByName(excursionName);
        if(opt_excursion.isPresent()){
            excursionRepository.delete(opt_excursion.get());
            return HttpStatus.OK;
        }else
            throw new ResponseNotFoundException("excursion with name '" + excursionName + "' not found.");

    }
}
