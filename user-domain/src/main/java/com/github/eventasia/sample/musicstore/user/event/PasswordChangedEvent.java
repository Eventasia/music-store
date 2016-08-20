package com.github.eventasia.sample.musicstore.user.event;

import java.util.UUID;

public class PasswordChangedEvent extends UserEvent {
    private UUID userId;
    private String newPassword;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
