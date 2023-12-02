package pl.lechowicz.queansserver.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lechowicz.queansserver.user.entity.Authorities;
import pl.lechowicz.queansserver.user.entity.AuthorityType;

import java.util.Optional;

public interface AuthoritiesRepository extends MongoRepository<Authorities, String> {
    Optional<Authorities> findByName(AuthorityType role);

}
