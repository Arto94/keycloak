package com.example.demo.controller;

import com.example.demo.model.User;
import io.swagger.annotations.Api;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@Api(value = "Users", description = "REST API for Users", tags = {"Users"})
public class UserController {


    @GetMapping(value = "/userProfile")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUserProfile(HttpServletRequest request) {
        Optional<User> userProfile = parseUserProfile(request);
        if (userProfile.isPresent()) {
            return ResponseEntity.ok(userProfile.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }


    private Optional<User> parseUserProfile(HttpServletRequest request) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        if (token != null) {
            KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
            KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
            return Optional.of(new User(session.getToken()));
        }
        return Optional.empty();
    }

}
