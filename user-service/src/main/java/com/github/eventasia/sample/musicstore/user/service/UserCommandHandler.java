package com.github.eventasia.sample.musicstore.user.service;

import com.github.eventasia.eventstore.command.AggregateCommandHandler;
import com.github.eventasia.eventstore.repository.AggregateRepository;
import com.github.eventasia.sample.musicstore.user.command.ModifyPasswordCommand;
import com.github.eventasia.sample.musicstore.user.event.PasswordChangedEvent;
import com.github.eventasia.sample.musicstore.user.event.UserCreatedEvent;
import com.github.eventasia.sample.musicstore.user.command.CreateUserCommand;
import com.github.eventasia.sample.musicstore.user.service.exception.UserIdAlreadyExistsException;
import com.github.eventasia.sample.musicstore.user.service.exception.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserCommandHandler {

    private final AggregateRepository<UserAggregate> userRepository;

    @Autowired
    public UserCommandHandler(final AggregateRepository<UserAggregate> userRepository) {
        this.userRepository = userRepository;
    }

    @AggregateCommandHandler
    public UserCreatedEvent handleCreateUserCommand(CreateUserCommand createUserCommand)
            throws UserIdAlreadyExistsException {
        /*
        You can't do that!
        Keep this kind of validation on the Client using the Query side!
        If the eventual consistency made a Constraint Violation of the Event,
         your Query side can call a RepairUserCommand or something like that :D
        (Dear user, can you use another username please? We have a little problem that will be solved now =] )
        if(usernameAlreadyExists(createUserCommand.getUsername())) {
            throw new UsernameAlreadyExistsException(createUserCommand.getUsername());
        }
        */

        //This may be unnecessary, fits better on the Client Side,
        // but you still can do if you can't deal with Eventual Consistency :)
        if (userExists(createUserCommand.getUserId())) {
            throw new UserIdAlreadyExistsException(createUserCommand.getUserId());
        }

        UserCreatedEvent event = new UserCreatedEvent();
        event.setUserId(createUserCommand.getUserId());
        event.setUsername(createUserCommand.getUsername());
        event.setPassword(createUserCommand.getPassword());

        return event;
    }

    @AggregateCommandHandler
    public PasswordChangedEvent handleModifyPasswordCommand(ModifyPasswordCommand modifyPasswordCommand)
            throws UserNotExistsException {

        /* This is also unnecessary because we already did the validation on the Client Side.
         Validate with the Query Side on your Client before sending this command and the problem was solved.
        final UserAggregate user = userRepository.get(modifyPasswordCommand.getUserId());

        if(user == null) {
            throw new UserNotExistsException(modifyPasswordCommand.getUserId());
        }
        */

        PasswordChangedEvent event = new PasswordChangedEvent();
        event.setUserId(modifyPasswordCommand.getUserId());
        event.setNewPassword(modifyPasswordCommand.getPassword());

        return event;
    }

    private boolean userExists(UUID userId) {
        return userRepository.get(userId) != null;
    }

}
