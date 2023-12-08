package pl.lechowicz.queansserver.entry.service;

import org.springframework.stereotype.Service;
import pl.lechowicz.queansserver.entry.modelDTO.EntryDTO;
import pl.lechowicz.queansserver.entry.repository.EntryRepository;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Optional<EntryDTO> getSingleEntry(String entryId) {
        return this.entryRepository.findById(entryId).map(x -> new EntryDTO(
                (x.getQuestions() != null) ?
                        x.getQuestions().stream().map(QuestionEntity::getQuestion)
                                .collect(Collectors.toSet()) :
                        new HashSet<>(),
                (x.getAnswers() != null) ?
                        x.getAnswers().stream().map(AnswerEntity::getAnswer)
                                .collect(Collectors.toSet()) :
                        new HashSet<>()
        ));
    }
}
