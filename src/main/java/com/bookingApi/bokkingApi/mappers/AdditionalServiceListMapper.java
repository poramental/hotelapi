package com.bookingApi.bokkingApi.mappers;


import com.bookingApi.bokkingApi.DTO.AdditionalServiceDto;
import com.bookingApi.bokkingApi.models.AdditionalServiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AdditionalServiceMapper.class)
public interface AdditionalServiceListMapper {
    List<AdditionalServiceEntity> toEntityList(List<AdditionalServiceDto> additionalServiceDtoList);
    List<AdditionalServiceDto> toDtoList(List<AdditionalServiceEntity> additionalServiceDtoList);
}
