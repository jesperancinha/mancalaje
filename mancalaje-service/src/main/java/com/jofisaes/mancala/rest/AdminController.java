package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.services.admin.AdminService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_PUBLIC_USERS;

@RestController()
@RequestMapping(MANCALA_PUBLIC_USERS)
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping(value = "/oauth/token")
    public void revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            adminService.revokeToken(tokenId);
        }
    }
}
