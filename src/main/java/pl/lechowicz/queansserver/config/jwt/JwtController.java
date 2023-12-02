package pl.lechowicz.queansserver.config.jwt;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lechowicz.queansserver.config.authentication.IAuthenticationFacade;
import pl.lechowicz.queansserver.config.model.LoginCredentials;

@RestController
@RequestMapping("")
public class JwtController {
    @Value("${spring.profiles.active:Unknown}")
    private String activeProfile;
    private final IAuthenticationFacade authenticationFacade;

    public JwtController(IAuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }

    @GetMapping("/logoutUser")
    public void logout(HttpServletResponse response) {
        removeJwtCookie(response);
        removeEmailCookie(response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private ResponseCookie.ResponseCookieBuilder getResponseCookieBuilder(String cookieName) {
        return ResponseCookie
                .from(cookieName, null)
                .maxAge(0);
    }

    private void removeEmailCookie(HttpServletResponse response){
        ResponseCookie.ResponseCookieBuilder responseCookieBuilder = getResponseCookieBuilder("e-mail");
                if(activeProfile.equals("prod")) {
                    responseCookieBuilder
                            .sameSite("None")
                            .secure(true);
                } else {
                    responseCookieBuilder
                            .secure(false);
                }
        ResponseCookie responseCookie = responseCookieBuilder.build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    private void removeJwtCookie(HttpServletResponse response){
        ResponseCookie.ResponseCookieBuilder responseCookieBuilder = getResponseCookieBuilder("token");
                if(activeProfile.equals("prod")) {
                    responseCookieBuilder
                            .sameSite("None")
                            .secure(true);
                } else {
                    responseCookieBuilder
                            .secure(false);
                }
                ResponseCookie responseCookie = responseCookieBuilder
                        .httpOnly(true)
                        .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    @GetMapping("/api/isLogin")
    public String isLogin(){
        return String.format("User login: %s", this.authenticationFacade.getAuthentication().getName());
    }
}
