package com.github.eventasia.sample.musicstore.user.command;

import java.util.UUID;

public class CreateUserCommand extends UserCommand {
    private UUID userId;
    private String username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
