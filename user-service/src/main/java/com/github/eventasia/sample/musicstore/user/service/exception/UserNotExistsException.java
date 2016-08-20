package com.github.eventasia.sample.musicstore.user.service.exception;

import java.util.UUID;

public class UserNotExistsException extends IllegalCommandException {
    public UserNotExistsException(UUID userId) {
        super(userId.toString());
    }
}
