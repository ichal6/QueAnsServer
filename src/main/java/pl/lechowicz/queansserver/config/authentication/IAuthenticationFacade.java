package pl.lechowicz.queansserver.config.authentication;


import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
