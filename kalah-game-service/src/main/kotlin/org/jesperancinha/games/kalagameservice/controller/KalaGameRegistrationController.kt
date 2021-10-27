package org.jesperancinha.games.kalagameservice.controller

import org.jesperancinha.games.kalagameservice.service.PlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("log")
class KalaGameRegistrationController(private val playerService: PlayerService) {
    @PostMapping("/")
    fun createPlayer(principal: Principal) {
        playerService.createPlayer(principal.name)
    }

    @GetMapping("user")
    fun greetUser(principal: Principal): String {
        return principal.name
    }
}