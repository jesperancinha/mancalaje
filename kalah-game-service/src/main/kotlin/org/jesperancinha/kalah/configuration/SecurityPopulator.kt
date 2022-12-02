package org.jesperancinha.kalah.configuration

import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource

/**
 * Created by jofisaes on 06/09/2021
 */
@Configuration
class SecurityPopulator(
    applicationContext: ApplicationContext,
    dataSource: DataSource,
    passwordEncoder: PasswordEncoder,
    jdbcUserDetailsManager: JdbcUserDetailsManager,
) {
    private val applicationContext: ApplicationContext
    private val dataSource: DataSource
    private val passwordEncoder: PasswordEncoder
    private val jdbcUserDetailsManager: JdbcUserDetailsManager

    @Bean
    open fun runner(): CommandLineRunner {
        return CommandLineRunner {
            val resource = applicationContext.getResource("classpath:schema.sql")
            ScriptUtils.executeSqlScript(dataSource.connection, resource)
            jdbcUserDetailsManager.createUser(
                User.withUsername("player1")
                    .password(passwordEncoder.encode("admin")).roles("ADMIN").build()
            )
            jdbcUserDetailsManager.createUser(
                User.withUsername("player2")
                    .password(passwordEncoder.encode("admin")).roles("ADMIN").build()
            )
        }
    }

    init {
        this.applicationContext = applicationContext
        this.dataSource = dataSource
        this.passwordEncoder = passwordEncoder
        this.jdbcUserDetailsManager = jdbcUserDetailsManager
    }
}
