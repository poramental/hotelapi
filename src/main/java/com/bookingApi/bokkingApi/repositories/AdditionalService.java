package com.bookingApi.bokkingApi.repositories;

import com.bookingApi.bokkingApi.models.AdditionalServiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdditionalService extends JpaRepository<AdditionalServiceEntity, UUID> {

}
