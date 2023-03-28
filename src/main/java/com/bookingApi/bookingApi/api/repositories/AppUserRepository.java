package com.bookingApi.bookingApi.api.repositories;

import com.bookingApi.bookingApi.api.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser,UUID> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByLogin(String login);
}
