package com.bookingApi.bokkingApi.mappers;


import com.bookingApi.bokkingApi.DTO.AdditionalServiceDto;
import com.bookingApi.bokkingApi.models.AdditionalServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdditionalServiceMapper {

    AdditionalServiceMapper INSTANCE = Mappers.getMapper(AdditionalServiceMapper.class);
    AdditionalServiceEntity toEntity(AdditionalServiceDto additionalServiceDto);

    AdditionalServiceDto toDto(AdditionalServiceEntity additionalServiceEntity);

}
