package org.jesperancinha.kalah.controller

import org.jesperancinha.kalah.service.KalahPlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("log")
class KalaGameRegistrationController(private val playerService: KalahPlayerService) {
    @PostMapping("/")
    fun createPlayer(principal: Principal) {
        playerService.createPlayer(principal.name)
    }

    @GetMapping("user")
    fun greetUser(principal: Principal): String {
        return principal.name
    }
}