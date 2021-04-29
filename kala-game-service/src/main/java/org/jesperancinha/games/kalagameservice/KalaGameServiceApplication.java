package org.jesperancinha.games.kalagameservice;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class KalaGameServiceApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(KalaGameServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
