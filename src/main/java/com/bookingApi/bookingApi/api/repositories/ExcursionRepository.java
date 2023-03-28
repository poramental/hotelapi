package com.bookingApi.bookingApi.api.repositories;

import com.bookingApi.bookingApi.api.models.ExcursionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExcursionRepository extends JpaRepository<ExcursionEntity, UUID> {

    Optional<ExcursionEntity> findByName(String excursionName);


}
