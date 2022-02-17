package co.bank.user.cmd.api.controllers;

import co.bank.user.cmd.api.commands.RegisterUserCommand;
import co.bank.user.cmd.api.dto.RegisterUserResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/register-user")
public class RegisterUserController {
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        command.setId(UUID.randomUUID().toString());

        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new RegisterUserResponse(command.getId(), "User successfully regiestered!"), HttpStatus.CREATED);
        }catch (Exception e) {
            var safeErrorMessage = "Error while processing register user reequest for id - " + command.getId();
            log.error(e.toString());
            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
