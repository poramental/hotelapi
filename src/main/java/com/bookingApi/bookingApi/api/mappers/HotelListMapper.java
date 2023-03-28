package com.bookingApi.bookingApi.api.mappers;


import com.bookingApi.bookingApi.api.DTO.HotelDto;
import com.bookingApi.bookingApi.api.models.HotelEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        HotelMapper.class,
        ExcursionListMapper.class,
        RoomListMapper.class,
        AdditionalServiceListMapper.class
})
public interface HotelListMapper {


    List<HotelDto> toDtoList(List<HotelEntity> hotelEntityList);

    List<HotelEntity> toEntityList(List<HotelDto> hotelDtoList);

}
