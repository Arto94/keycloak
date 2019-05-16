package com.example.demo.config;


import org.keycloak.KeycloakPrincipal;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulLoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        KeycloakPrincipal userDetails = (KeycloakPrincipal) event.getAuthentication().getPrincipal();

    }
}
