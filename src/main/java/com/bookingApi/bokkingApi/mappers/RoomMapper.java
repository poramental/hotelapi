package com.bookingApi.bokkingApi.mappers;


import com.bookingApi.bokkingApi.DTO.RoomDto;
import com.bookingApi.bokkingApi.models.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDto toDto(RoomEntity room);


    RoomEntity toEntity(RoomDto roomDto);
}
