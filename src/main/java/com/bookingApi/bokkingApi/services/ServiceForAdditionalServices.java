package com.bookingApi.bokkingApi.services;


import com.bookingApi.bokkingApi.DTO.AdditionalServiceDto;
import com.bookingApi.bokkingApi.Exceptions.ObjectIsPresentException;
import com.bookingApi.bokkingApi.Exceptions.ResponseNotFoundException;
import com.bookingApi.bokkingApi.mappers.AdditionalServiceListMapper;
import com.bookingApi.bokkingApi.mappers.AdditionalServiceMapper;
import com.bookingApi.bokkingApi.models.AdditionalServiceEntity;
import com.bookingApi.bokkingApi.models.HotelEntity;
import com.bookingApi.bokkingApi.repositories.AdditionalServiceRepo;
import com.bookingApi.bokkingApi.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceForAdditionalServices {
    private final AdditionalServiceMapper additionalServiceMapper;
    private final AdditionalServiceListMapper additionalServiceListMapper;
    private  final AdditionalServiceRepo additionalServiceRepo;

    private final HotelRepository hotelRepository;

    public HttpStatus createService(AdditionalServiceDto serviceDto){
        if(!additionalServiceRepo.findByName(serviceDto.getName()).isPresent()){
            AdditionalServiceEntity serviceEntity = additionalServiceMapper.toEntity(serviceDto);
            serviceEntity.setId(UUID.randomUUID());
            additionalServiceRepo.save(serviceEntity);
            return HttpStatus.OK;
        }else throw new ObjectIsPresentException("service with name '" + serviceDto.getName() + "' is present.");
    }

    public ResponseEntity<List<AdditionalServiceDto>> getServices(String hotelName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            return new ResponseEntity<>(additionalServiceListMapper.toDtoList(opt_hotel.get().getServices()),HttpStatus.OK);
        }else
            throw new ResponseNotFoundException("hotel with name '" + hotelName + "' not found.");
    }

    public HttpStatus addServiceToHotel(String hotelName,String serviceName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        if(opt_hotel.isPresent()){
            Optional<AdditionalServiceEntity> opt_service = additionalServiceRepo.findByName(serviceName);
            if(opt_service.isPresent()){
                additionalServiceRepo.save(opt_service.get().addHotel(opt_hotel.get()));
                hotelRepository.save(opt_hotel.get().addService(opt_service.get()));
                return HttpStatus.OK;
            } else
                throw new ResponseNotFoundException("service with name '" + serviceName + "' not found.");
        }else
            throw new ResponseNotFoundException("hotel with name '" + hotelName + "' not found.");

    }
    public ResponseEntity<List<AdditionalServiceDto>> getServices(){
        return new ResponseEntity<>(additionalServiceListMapper.toDtoList(additionalServiceRepo.findAll()),
                HttpStatus.OK);
    }

    public HttpStatus deleteService(String serviceName){
        Optional<AdditionalServiceEntity> opt_service = additionalServiceRepo.findByName(serviceName);
        if(opt_service.isPresent()){
            additionalServiceRepo.delete(opt_service.get());
            return HttpStatus.OK;
        }else
            throw new ResponseNotFoundException("service with name '" + serviceName + "' not found.");
    }

    public HttpStatus deleteServiceFromHotel(String hotelName, String serviceName){
        Optional<HotelEntity> opt_hotel = hotelRepository.findByName(hotelName);
        Optional<AdditionalServiceEntity> opt_service = additionalServiceRepo.findByName(serviceName);
        if(opt_hotel.isPresent()){
            if(opt_service.isPresent()){
                opt_service.get().removeHotel(opt_hotel.get());
                opt_hotel.get().removeService(opt_service.get());
                hotelRepository.save(opt_hotel.get());
                additionalServiceRepo.save(opt_service.get());
                return HttpStatus.OK;
            }else
                throw new ResponseNotFoundException("service with name '" + serviceName + "' not found.");
        }else
            throw new ResponseNotFoundException("hotel with name '" + hotelName + "' not found.");
    }


    public HttpStatus updateService(String serviceName, AdditionalServiceDto additionalServiceDto){
        Optional<AdditionalServiceEntity> opt_service = additionalServiceRepo.findByName(serviceName);
        if(opt_service.isPresent()){
            if(!additionalServiceDto.getName().equals("") && additionalServiceDto.getName() != null){
                opt_service.get().setName(additionalServiceDto.getName());
            }
            if(!additionalServiceDto.getDescription().equals("") && additionalServiceDto.getDescription() != null){
                opt_service.get().setDescription(additionalServiceDto.getDescription());
            }
            if(additionalServiceDto.getPrice() != 0.0){
                opt_service.get().setPrice(additionalServiceDto.getPrice());
            }
            additionalServiceRepo.save(opt_service.get());
            return HttpStatus.OK;

        }else
            throw new ResponseNotFoundException("service with name '" + serviceName + "' not found.");
    }
}
