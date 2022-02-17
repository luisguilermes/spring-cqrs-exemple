package co.bank.user.query.api.handlers;

import co.bank.user.core.events.UserRegisteredEvent;
import co.bank.user.core.events.UserRemovedEvent;
import co.bank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
