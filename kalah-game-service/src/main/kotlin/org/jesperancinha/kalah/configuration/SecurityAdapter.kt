package org.jesperancinha.kalah.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager

/**
 * Created by jofisaes on 06/09/2021
 */

@Configuration
open class SecurityAdapter(jdbcUserDetailsManager: JdbcUserDetailsManager, passwordEncoder: PasswordEncoder) :
    WebSecurityConfigurerAdapter() {
    private val jdbcUserDetailsManager: JdbcUserDetailsManager
    private val passwordEncoder: PasswordEncoder

    @kotlin.Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/**")
            .authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("http://localhost:4200")
            .and().csrf().disable()
    }

    @kotlin.Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(jdbcUserDetailsManager).passwordEncoder(passwordEncoder)
    }

    init {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager
        this.passwordEncoder = passwordEncoder
    }
}
