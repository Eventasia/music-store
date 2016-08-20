package com.github.eventasia.sample.musicstore.user.service.dto;

import java.io.Serializable;
import java.util.UUID;

public class ModifyPasswordRequest implements Serializable {
    private UUID userId;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
