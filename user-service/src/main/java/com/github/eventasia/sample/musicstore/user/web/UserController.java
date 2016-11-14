package com.github.eventasia.sample.musicstore.user.web;

import com.github.eventasia.sample.musicstore.user.command.CreateUserCommand;
import com.github.eventasia.sample.musicstore.user.command.ModifyPasswordCommand;
import com.github.eventasia.sample.musicstore.user.service.UserService;
import com.github.eventasia.sample.musicstore.user.dto.CreateUserRequest;
import com.github.eventasia.sample.musicstore.user.dto.CreateUserResponse;
import com.github.eventasia.sample.musicstore.user.dto.ModifyPasswordRequest;
import com.github.eventasia.sample.musicstore.user.service.exception.IllegalCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody final CreateUserRequest request) {

        try {
            final CreateUserCommand command = new CreateUserCommand();
            command.setUsername(request.getUsername());
            command.setPassword(request.getPassword());

            userService.createUser(command);

            final CreateUserResponse userCreatedResponse = new CreateUserResponse();
            return ResponseEntity.accepted().body(userCreatedResponse);
        } catch (IllegalCommandException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

    @RequestMapping(value = "/v1/user/password", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody final ModifyPasswordRequest request) {

        try {
            final ModifyPasswordCommand command = new ModifyPasswordCommand();
            command.setUserId(request.getUserId());
            command.setPassword(request.getPassword());

            userService.modifyPassword(command);

            final CreateUserResponse userCreatedResponse = new CreateUserResponse();
            return ResponseEntity.accepted().body(userCreatedResponse);
        } catch (IllegalCommandException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }

}
