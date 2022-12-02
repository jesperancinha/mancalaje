package org.jesperancinha.kalah.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

/**
 * Created by jofisaes on 06/09/2021
 */

@Configuration
open class SecurityAdapter(jdbcUserDetailsManager: JdbcUserDetailsManager, passwordEncoder: PasswordEncoder) {
    private val jdbcUserDetailsManager: JdbcUserDetailsManager
    private val passwordEncoder: PasswordEncoder


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http
        .userDetailsService(jdbcUserDetailsManager)
        .authorizeRequests()
        .requestMatchers("/**")
        .authenticated()
        .and()
        .formLogin()
        .defaultSuccessUrl("http://localhost:4200")
        .and().csrf().disable().build()

    init {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager
        this.passwordEncoder = passwordEncoder
    }
}
