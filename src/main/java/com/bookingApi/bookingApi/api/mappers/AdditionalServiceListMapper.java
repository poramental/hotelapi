package com.bookingApi.bookingApi.api.mappers;


import com.bookingApi.bookingApi.api.DTO.AdditionalServiceDto;
import com.bookingApi.bookingApi.api.models.AdditionalServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = AdditionalServiceMapper.class)
public interface AdditionalServiceListMapper {

    AdditionalServiceListMapper INSTANCE = Mappers.getMapper(AdditionalServiceListMapper.class);
    List<AdditionalServiceEntity> toEntityList(List<AdditionalServiceDto> additionalServiceDtoList);
    List<AdditionalServiceDto> toDtoList(List<AdditionalServiceEntity> additionalServiceDtoList);
}
