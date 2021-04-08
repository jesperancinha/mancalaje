package org.jesperancinha.games.kalagameservice;

import org.jesperancinha.games.kalagameservice.model.Stone;
import org.jesperancinha.games.kalagameservice.repository.KalaStoneRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class KalaGameServiceApplication implements ApplicationRunner {

    private final KalaStoneRepository stoneRepository;

    public KalaGameServiceApplication(KalaStoneRepository stoneRepository) {
        this.stoneRepository = stoneRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(KalaGameServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.stoneRepository.save(Stone.builder().build());
    }
}
