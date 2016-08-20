package com.github.eventasia.sample.musicstore.user.service.exception;

import java.util.UUID;

public class UserIdAlreadyExistsException extends IllegalCommandException {
    public UserIdAlreadyExistsException(UUID userId) {
        super(userId.toString());
    }
}
