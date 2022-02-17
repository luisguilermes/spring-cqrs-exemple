package co.bank.user.query.api.handlers;

import co.bank.user.query.api.dto.UserLookupResponse;
import co.bank.user.query.api.queries.FindAllUsersQuery;
import co.bank.user.query.api.queries.FindUserByIdQuery;
import co.bank.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
