package com.github.eventasia.sample.musicstore.user.service;

import com.github.eventasia.eventstore.AggregateService;
import com.github.eventasia.sample.musicstore.user.command.CreateUserCommand;
import com.github.eventasia.sample.musicstore.user.command.ModifyPasswordCommand;
import com.github.eventasia.sample.musicstore.user.command.UserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final AggregateService<User, UserCommand> aggregateService;

    @Autowired
    public UserServiceImpl(final AggregateService<User, UserCommand> aggregateService) {
        this.aggregateService = aggregateService;
    }

    @Override
    public UUID createUser(final CreateUserCommand command) {
        final UUID uuid = UUID.randomUUID();

        command.setUserId(uuid);

        aggregateService.process(command);

        return uuid;
    }

    @Override
    public void modifyPassword(ModifyPasswordCommand command) {
        aggregateService.process(command);
    }
}
