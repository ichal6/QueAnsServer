package pl.lechowicz.queansserver.user.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import pl.lechowicz.queansserver.common.entity.Entity;

import java.util.Objects;

public class Authorities extends Entity implements GrantedAuthority {
    @Indexed(unique = true)
    private AuthorityType name;

    public String getAuthority() {
        return this.name.name();
    }

    public AuthorityType getName() {
        return name;
    }

    public void setName(AuthorityType name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorities that = (Authorities) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Authorities(AuthorityType name) {
        this.name = name;
    }

    public Authorities() {
    }
}
