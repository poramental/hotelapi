package com.bookingApi.bokkingApi.repositories;

import com.bookingApi.bokkingApi.models.ExcursionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExcursionRepository extends JpaRepository<ExcursionEntity, UUID> {

}
