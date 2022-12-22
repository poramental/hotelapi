package com.bookingApi.bokkingApi.repository;

import com.bookingApi.bokkingApi.models.ExcursionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionRepository extends JpaRepository<ExcursionEntity, Long> {

}
