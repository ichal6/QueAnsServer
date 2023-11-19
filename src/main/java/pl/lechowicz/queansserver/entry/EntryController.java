package pl.lechowicz.queansserver.entry;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entry")
public class EntryController {
    private final EntryRepository entryRepository;
    public EntryController(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        entryRepository.deleteAll();
        var entry1 = new EntryEntity(Set.of(new QuestionEntity("What is Spring Boot?")),
                Set.of(new AnswerEntity("Spring Boot is a Java based spring framework, " +
                        "it provides Rapid application development features like auto-configuration, " +
                        "embedded servers, package structures."))
        );

        var entry2 = new EntryEntity(Set.of(new QuestionEntity("What is the difference between application.properties file and application.yml file?")),
                Set.of(new AnswerEntity("There are the files, where we mention in which port our application should run, " +
                        "what are the credentials required for db is mentioned in these"))
        );

        entryRepository.save(entry1);
        entryRepository.save(entry2);
    }

    @GetMapping
    public List<EntityDTO> getAll() {
        return this.entryRepository.findAll().stream()
                .map(x -> new EntityDTO(
                        x.getQuestions().stream().map(QuestionEntity::getQuestion)
                                .collect(Collectors.toSet()),
                        x.getAnswers().stream().map(AnswerEntity::getAnswer)
                                .collect(Collectors.toSet())
                )).toList();
    }
}
