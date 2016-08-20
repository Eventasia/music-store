package com.github.eventasia.sample.musicstore.user.service;

import com.github.eventasia.framework.Aggregate;

import java.util.UUID;

public class User implements Aggregate<User> {
    private UUID userId;
    private volatile long version;


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

    @Override
    public synchronized long getVersion() {
        return version;
    }

    @Override
    public synchronized long incrementVersion() {
        return version++;
    }
}
