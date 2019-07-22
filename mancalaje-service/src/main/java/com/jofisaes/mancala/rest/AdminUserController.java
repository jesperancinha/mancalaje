package com.jofisaes.mancala.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_ADMIN_USERS;

@RestController()
@RequestMapping(MANCALA_ADMIN_USERS)
public class AdminUserController {

}
