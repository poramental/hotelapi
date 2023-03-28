package com.bookingApi.bookingApi.api.mappers;


import com.bookingApi.bookingApi.api.DTO.ExcursionDto;
import com.bookingApi.bookingApi.api.models.ExcursionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ExcursionMapper.class)
public interface ExcursionListMapper {

    List<ExcursionEntity> toEntityList(List<ExcursionDto> excursionDtoList);

    List<ExcursionDto> toDtoList(List<ExcursionEntity> excursionEntityList);

}
