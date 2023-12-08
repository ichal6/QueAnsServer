package pl.lechowicz.queansserver.entry.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;

import java.util.Set;

public interface AnswerRepository extends MongoRepository<AnswerEntity, String> {
    Set<AnswerEntity> findByParentId(String entryId);
}
