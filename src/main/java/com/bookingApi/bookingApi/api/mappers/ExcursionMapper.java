package com.bookingApi.bookingApi.api.mappers;


import com.bookingApi.bookingApi.api.DTO.ExcursionDto;
import com.bookingApi.bookingApi.api.models.ExcursionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExcursionMapper {


    ExcursionMapper INSTANCE = Mappers.getMapper(ExcursionMapper.class);
    ExcursionEntity toEntity(ExcursionDto excursionDto);

    ExcursionDto toDto(ExcursionEntity excursion);
}
