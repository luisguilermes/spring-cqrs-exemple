package co.bank.user.query.api;

import co.bank.user.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class, args);
    }
}
