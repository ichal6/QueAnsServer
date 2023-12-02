package pl.lechowicz.queansserver.user.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import pl.lechowicz.queansserver.common.Entity;

import java.util.Objects;
import java.util.Set;


public class User extends Entity {
    //@Indexed(unique = true)
    private String email;

    private String name;
    private String hashPassword;
    private Set<Authorities> authorities;
    private int enabled;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled && Objects.equals(email, user.email) && Objects.equals(hashPassword, user.hashPassword) && Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, hashPassword, authorities, enabled);
    }

    public User() {
    }

    public User(String email, String hashPassword, Set<Authorities> authorities, int enabled) {
        this.email = email;
        this.hashPassword = hashPassword;
        this.authorities = authorities;
        this.enabled = enabled;
    }
}
