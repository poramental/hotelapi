package com.bookingApi.bookingApi.api.mappers;


import com.bookingApi.bookingApi.api.DTO.RoomDto;
import com.bookingApi.bookingApi.api.models.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDto toDto(RoomEntity room);


    RoomEntity toEntity(RoomDto roomDto);
}
