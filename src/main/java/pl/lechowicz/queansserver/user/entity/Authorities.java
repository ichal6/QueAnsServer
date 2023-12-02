package pl.lechowicz.queansserver.user.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import pl.lechowicz.queansserver.common.Entity;

import java.util.Objects;
import java.util.Set;

public class Authorities extends Entity implements GrantedAuthority {
    @Indexed(unique = true)
    private AuthorityType name;

    Set<User> users;

    public String getAuthority() {
        return this.name.name();
    }

    public AuthorityType getName() {
        return name;
    }

    public void setName(AuthorityType name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorities that = (Authorities) o;
        return name == that.name && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users);
    }

    public Authorities(AuthorityType name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public Authorities(AuthorityType name) {
        this.name = name;
    }

    public Authorities() {
    }
}
