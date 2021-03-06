package de.fhw.flippermods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class FlippermodsApplication {


    public static void main(String[] args) {
        SpringApplication.run(FlippermodsApplication.class, args);
    }

}
