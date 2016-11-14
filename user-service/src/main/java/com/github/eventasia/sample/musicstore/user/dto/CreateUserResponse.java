package com.github.eventasia.sample.musicstore.user.dto;

import java.io.Serializable;
import java.util.UUID;

public class CreateUserResponse implements Serializable {

    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
