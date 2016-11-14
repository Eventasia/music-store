package com.github.eventasia.sample.musicstore.user.service;

import com.github.eventasia.eventstore.repository.AggregateRepository;
import com.github.eventasia.sample.musicstore.user.event.UserCreatedEvent;
import com.github.eventasia.sample.musicstore.user.command.CreateUserCommand;
import com.github.eventasia.sample.musicstore.user.service.exception.UserIdAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAggregateCommandHandlerTest {

    @Mock
    private AggregateRepository<UserAggregate> userRepository;

    private UserCommandHandler userCommandHandler;

    @Before
    public void setUp() {
        userCommandHandler = new UserCommandHandler(userRepository);
    }


    @Test
    public void handleCreateUserCommand() throws UserIdAlreadyExistsException {

        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setUserId(UUID.randomUUID());
        createUserCommand.setUsername("username");
        createUserCommand.setPassword("password");

        Mockito.when(userRepository.get(Mockito.any())).thenReturn(null);

        UserCreatedEvent userCreatedEvent = userCommandHandler.handleCreateUserCommand(createUserCommand);

        assertThat(userCreatedEvent.getUserId()).isEqualTo(createUserCommand.getAggregateId());
        assertThat(userCreatedEvent.getUsername()).isEqualTo(createUserCommand.getUsername());
        assertThat(userCreatedEvent.getPassword()).isEqualTo(createUserCommand.getPassword());

    }


    @Test
    public void handleCreateUserCommand_existentUser() throws Exception {

        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setUserId(UUID.randomUUID());

        Mockito.when(userRepository.get(Mockito.any())).thenReturn(new UserAggregate());

        assertThatThrownBy(() -> { userCommandHandler.handleCreateUserCommand(createUserCommand); })
                .isInstanceOf(UserIdAlreadyExistsException.class);

    }

}