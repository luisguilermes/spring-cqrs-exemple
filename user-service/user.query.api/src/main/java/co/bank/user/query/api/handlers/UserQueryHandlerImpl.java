package co.bank.user.query.api.handlers;

import co.bank.user.query.api.dto.UserLookupResponse;
import co.bank.user.query.api.queries.FindAllUsersQuery;
import co.bank.user.query.api.queries.FindUserByIdQuery;
import co.bank.user.query.api.queries.SearchUsersQuery;
import co.bank.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserRepository userRepository;

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        return user.isPresent() ?  new UserLookupResponse(user.get()) : null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        var users = new ArrayList<>(userRepository.findByFilterRegex(query.getFilter()));
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        var users = new ArrayList<>(userRepository.findAll());
        return new UserLookupResponse(users);
    }
}
