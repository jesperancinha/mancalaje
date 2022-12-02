package org.jesperancinha.kalah

import org.jesperancinha.kalah.containers.AbstractTestContainersIT
import org.jesperancinha.kalah.containers.AbstractTestContainersIT.DockerPostgresDataInitializer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(initializers = [DockerPostgresDataInitializer::class])
internal class KalaGameServiceImplApplicationTests {
    @Test
    fun contextLoads() {
    }
}