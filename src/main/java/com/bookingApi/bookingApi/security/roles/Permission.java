package com.bookingApi.bookingApi.security.roles;

import lombok.Getter;
import org.springframework.util.SimpleIdGenerator;

@Getter
public enum Permission {

    HOTELS_READ("hotels:read"),
    HOTELS_WRITE("hotels:write");

    public final String permission;

    Permission(String permission){
        this.permission = permission;
    }



}
