package com.bookingApi.bokkingApi.repositories;

import com.bookingApi.bokkingApi.models.AdditionalServiceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdditionalServiceRepo extends JpaRepository<AdditionalServiceEntity, UUID> {
    Optional<AdditionalServiceEntity> findByName(String ServiceName);
}
