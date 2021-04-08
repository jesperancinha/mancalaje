package org.jesperancinha.games.kalagameservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class KalaGameRegistrationController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome !";
    }

    @GetMapping("/user")
    public String greetUser(Principal principal) {
        return String.format("Hello, %s", principal.getName());
    }

    @GetMapping("/admin")
    public String greetAdmin(Principal principal) {
        return String.format("Hello, %s", principal.getName());
    }
}
