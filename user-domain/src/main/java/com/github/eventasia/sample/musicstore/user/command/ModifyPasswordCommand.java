package com.github.eventasia.sample.musicstore.user.command;

import java.util.UUID;

public class ModifyPasswordCommand extends UserCommand {
    private UUID userId;
    private String password;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public UUID getAggregateId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
