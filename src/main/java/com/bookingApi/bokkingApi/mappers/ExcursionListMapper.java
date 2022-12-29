package com.bookingApi.bokkingApi.mappers;


import com.bookingApi.bokkingApi.DTO.AdditionalServiceDto;
import com.bookingApi.bokkingApi.DTO.ExcursionDto;
import com.bookingApi.bokkingApi.models.AdditionalServiceEntity;
import com.bookingApi.bokkingApi.models.ExcursionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ExcursionMapper.class)
public interface ExcursionListMapper {

    List<ExcursionEntity> toEntityList(List<ExcursionDto> excursionDtoList);

    List<ExcursionDto> toDtoList(List<ExcursionEntity> excursionEntityList);

}
