package co.bank.user.cmd.api.dto;

public class RegisterUserResponse extends BaseResponse {
    private String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }

    public RegisterUserResponse(String message) {
        super(message);
    }
}
