package com.bookingApi.bookingApi.api.mappers;
import com.bookingApi.bookingApi.api.DTO.HotelDto;
import com.bookingApi.bookingApi.api.models.HotelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
        RoomListMapper.class,
        ExcursionListMapper.class,
        AdditionalServiceListMapper.class
})
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    HotelDto toDto(HotelEntity hotel);

    HotelEntity toEntity(HotelDto hotelDto);
}
