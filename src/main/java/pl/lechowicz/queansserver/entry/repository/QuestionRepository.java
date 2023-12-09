package pl.lechowicz.queansserver.entry.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;

import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, String> {
    @Aggregation("{ $sample: { size: 1 } }")
    Optional<QuestionEntity> findRandom();

    Set<QuestionEntity> findByParentId(String entryId);
}
