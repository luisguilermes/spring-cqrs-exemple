package co.bank.user.query.api.controllers;

import co.bank.user.query.api.dto.UserLookupResponse;
import co.bank.user.query.api.queries.FindAllUsersQuery;
import co.bank.user.query.api.queries.FindUserByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserLookupController {
    private final QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        try {
            var query = new FindAllUsersQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (response == null  ||  response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all users request";
            log.error(e.toString());
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id) {
        try {
            var query = new FindUserByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (response == null  ||  response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            var safeErrorMessage = "Failed to complete get user by ID request";
            log.error(e.toString());
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
