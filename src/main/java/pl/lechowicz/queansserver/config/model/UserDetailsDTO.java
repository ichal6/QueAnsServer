package pl.lechowicz.queansserver.config.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.lechowicz.queansserver.user.entity.Authorities;
import pl.lechowicz.queansserver.user.entity.User;

import java.util.Collection;
import java.util.Set;

public record UserDetailsDTO(String email, String password, Set<Authorities> authorities, boolean isEnabled)
        implements UserDetails {
    public UserDetailsDTO(User userInfo) {
        this(userInfo.getEmail(),
             userInfo.getHashPassword(),
             userInfo.getAuthorities(),
             userInfo.getEnabled() > 0);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
