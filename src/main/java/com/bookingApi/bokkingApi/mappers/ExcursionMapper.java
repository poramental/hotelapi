package com.bookingApi.bokkingApi.mappers;


import com.bookingApi.bokkingApi.DTO.ExcursionDto;
import com.bookingApi.bokkingApi.models.ExcursionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExcursionMapper {


    ExcursionMapper INSTANCE = Mappers.getMapper(ExcursionMapper.class);
    ExcursionEntity toEntity(ExcursionDto excursionDto);

    ExcursionDto toDto(ExcursionEntity excursion);
}
