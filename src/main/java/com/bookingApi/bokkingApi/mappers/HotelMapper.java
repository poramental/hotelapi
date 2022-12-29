package com.bookingApi.bokkingApi.mappers;
import com.bookingApi.bokkingApi.DTO.HotelDto;
import com.bookingApi.bokkingApi.models.HotelEntity;
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
