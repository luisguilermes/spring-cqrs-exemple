package co.bank.user.query.api.handlers;

import co.bank.user.core.events.UserRegisteredEvent;
import co.bank.user.core.events.UserRemovedEvent;
import co.bank.user.core.events.UserUpdatedEvent;
import co.bank.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {
    private final UserRepository userRepository;

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
