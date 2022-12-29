package com.bookingApi.bokkingApi.mappers;


import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
