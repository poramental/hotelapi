package com.bookingApi.bokkingApi.mappers;


import com.bookingApi.bokkingApi.DTO.RoomDto;
import com.bookingApi.bokkingApi.models.RoomEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface RoomListMapper {
    List<RoomDto> toDtoList(List<RoomEntity> rooms);

    List<RoomEntity> toEntityList(List<RoomDto> roomsDTO);

}
