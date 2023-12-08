package pl.lechowicz.queansserver.entry.service;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import pl.lechowicz.queansserver.common.exception.ResourceNotFoundException;
import pl.lechowicz.queansserver.entry.controller.AnswerController;
import pl.lechowicz.queansserver.entry.controller.EntryController;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.modelDTO.EntryDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleQuestionDTO;
import pl.lechowicz.queansserver.entry.repository.EntryRepository;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;
import pl.lechowicz.queansserver.entry.repository.QuestionRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    private final QuestionRepository questionRepository;

    public EntryService(EntryRepository entryRepository, QuestionRepository questionRepository) {
        this.entryRepository = entryRepository;
        this.questionRepository = questionRepository;
    }

    public Optional<EntryDTO> getSingleEntry(String entryId) {
        return this.entryRepository.findById(entryId).map(mapToDto());
    }

    public Set<SingleQuestionDTO> getQuestionsForEntry(String entryId) {
        if(!this.entryRepository.existsById(entryId))
            throw new ResourceNotFoundException(ResourceNotFoundException.Message.THE_ENTRY_IS_NOT_EXISTS);
        return this.questionRepository.findByParentId(entryId).stream()
                .map(QuestionService.mapToDto())
                .collect(Collectors.toUnmodifiableSet());
    }

    private Function<EntryEntity, EntryDTO> mapToDto() {
        return entry -> {
            var dto = new EntryDTO(
                    (entry.getQuestions() != null) ?
                            entry.getQuestions().stream().map(QuestionEntity::getQuestion)
                                    .collect(Collectors.toSet()) :
                            new HashSet<>(),
                    (entry.getAnswers() != null) ?
                            entry.getAnswers().stream().map(AnswerEntity::getAnswer)
                                    .collect(Collectors.toSet()) :
                            new HashSet<>());
            Link linkToQuestions = WebMvcLinkBuilder.linkTo(EntryController.class).slash(entry.getId()).slash("questions").withRel("questions");
            Link linkToAnswers = WebMvcLinkBuilder.linkTo(AnswerController.class).slash(entry.getId()).withRel("answers");
            dto.add(linkToQuestions);
            dto.add(linkToAnswers);
            return dto;
        };
    }
}
