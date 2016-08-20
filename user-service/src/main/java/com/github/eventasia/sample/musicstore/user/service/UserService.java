package com.github.eventasia.sample.musicstore.user.service;

import com.github.eventasia.sample.musicstore.user.command.CreateUserCommand;
import com.github.eventasia.sample.musicstore.user.command.ModifyPasswordCommand;
import com.github.eventasia.sample.musicstore.user.service.exception.IllegalCommandException;

import java.util.UUID;

public interface UserService {
    UUID createUser(CreateUserCommand command) throws IllegalCommandException;

    void modifyPassword(ModifyPasswordCommand command) throws IllegalCommandException;
}
