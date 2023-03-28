package com.bookingApi.bookingApi.api.mappers;


import com.bookingApi.bookingApi.api.DTO.AdditionalServiceDto;
import com.bookingApi.bookingApi.api.models.AdditionalServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdditionalServiceMapper {

    AdditionalServiceMapper INSTANCE = Mappers.getMapper(AdditionalServiceMapper.class);
    AdditionalServiceEntity toEntity(AdditionalServiceDto additionalServiceDto);

    AdditionalServiceDto toDto(AdditionalServiceEntity additionalServiceEntity);

}
