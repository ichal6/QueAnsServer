package pl.lechowicz.queansserver.entry.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;

public interface AnswerRepository extends MongoRepository<AnswerEntity, String> {
}
