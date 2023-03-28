package com.bookingApi.bookingApi.api.mappers;


import com.bookingApi.bookingApi.api.DTO.RoomDto;
import com.bookingApi.bookingApi.api.models.RoomEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface RoomListMapper {
    List<RoomDto> toDtoList(List<RoomEntity> rooms);

    List<RoomEntity> toEntityList(List<RoomDto> roomsDTO);

}
