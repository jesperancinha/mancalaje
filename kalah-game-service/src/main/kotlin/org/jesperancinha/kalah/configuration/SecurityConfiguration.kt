package org.jesperancinha.kalah.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource

/**
 * Created by jofisaes on 06/09/2021
 */
@Configuration
open class SecurityConfiguration(dataSource: DataSource) {
    private val dataSource: DataSource

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    open fun userDetailsManager(): JdbcUserDetailsManager {
        return JdbcUserDetailsManager(dataSource)
    }

    init {
        this.dataSource = dataSource
    }
}
