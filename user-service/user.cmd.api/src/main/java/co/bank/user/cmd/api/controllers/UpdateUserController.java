package co.bank.user.cmd.api.controllers;

import co.bank.user.cmd.api.commands.UpdateUserCommand;
import co.bank.user.cmd.api.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/update-user")
public class UpdateUserController {
    private final CommandGateway commandGateway;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody UpdateUserCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("User successfully updated"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing update user reequest for id - " + command.getId();
            log.error(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
