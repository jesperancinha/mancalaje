package com.jofisaes.mancala.config;

import com.jofisaes.mancala.services.authentication.DefaultUserDetailsService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import java.net.URI;

@EnableJpaAuditing
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.jofisaes.mancala.repository",
        transactionManagerRef = "mancalaJeTransactionManager")
@Configuration
@Profile({"dev", "prod"})
public class MancalaConfiguration {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private DefaultUserDetailsService userDetailsService;

    public MancalaConfiguration(DefaultUserDetailsService userDetailsService) throws Exception {
        this.userDetailsService = userDetailsService;
        BrokerService broker = BrokerFactory.createBroker(new URI(
                "broker:(tcp://localhost:61616)"));
        broker.start();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return getPasswordEncoder();
    }

    private PasswordEncoder getPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(
                "tcp://localhost:61616");
    }

    @Bean(name = "mancalaJeTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
