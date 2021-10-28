package org.jesperancinha.kalah

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
@EnableAspectJAutoProxy(proxyTargetClass=true)
class KalaGameServiceApplication : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(KalaGameServiceApplication::class.java, *args)
        }
    }
}