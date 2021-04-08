package org.jesperancinha.games.kalagameservice.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityPopulator {

    private final ApplicationContext applicationContext;
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final JdbcUserDetailsManager jdbcUserDetailsManager;

    public SecurityPopulator(
            ApplicationContext applicationContext,
            DataSource dataSource,
            PasswordEncoder passwordEncoder,
            JdbcUserDetailsManager jdbcUserDetailsManager) {
        this.applicationContext = applicationContext;
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            var resource = applicationContext.getResource("classpath:schema.sql");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
            jdbcUserDetailsManager.createUser(
                    User.withUsername("player1").password(passwordEncoder.encode("admin")).roles("ADMIN").build()
            );
            jdbcUserDetailsManager.createUser(
                    User.withUsername("player2").password(passwordEncoder.encode("admin")).roles("ADMIN").build()
            );
        };
    }
}
