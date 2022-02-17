package co.bank.user.query.api.dto;

import co.bank.user.core.dto.BaseResponse;
import co.bank.user.core.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserLookupResponse extends BaseResponse {
    private List<User> users;

    public UserLookupResponse(String message) {
        super(message);
    }

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookupResponse(String message, User user) {
        super(message);
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public UserLookupResponse(User user) {
        super(null);
        this.users = new ArrayList<>();
        this.users.add(user);
    }
}
