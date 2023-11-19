package pl.lechowicz.queansserver.entry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;

@Repository
public interface EntryRepository extends MongoRepository<EntryEntity, String> {}
