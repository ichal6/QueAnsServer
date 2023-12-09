package pl.lechowicz.queansserver.entry.service;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lechowicz.queansserver.common.exception.ResourceNotFoundException;
import pl.lechowicz.queansserver.entry.controller.EntryController;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;
import pl.lechowicz.queansserver.entry.modelDTO.NewQuestionDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleQuestionDTO;
import pl.lechowicz.queansserver.entry.repository.EntryRepository;
import pl.lechowicz.queansserver.entry.repository.QuestionRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.Function;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final EntryRepository entryRepository;

    public QuestionService(QuestionRepository questionRepository, EntryRepository entryRepository) {
        this.questionRepository = questionRepository;
        this.entryRepository = entryRepository;
    }

    public SingleQuestionDTO getRandomQuestion() {
        return this.questionRepository.findRandom()
                    .map(mapToDto())
                    .orElseThrow(()-> new ResourceNotFoundException(ResourceNotFoundException.Message.THE_SET_IS_EMPTY));
    }

    @Transactional
    public SingleQuestionDTO addQuestion(NewQuestionDTO questionDTO, String entryId) {
        EntryEntity entry = this.entryRepository.findById(entryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(ResourceNotFoundException.Message.THE_ENTRY_IS_NOT_EXISTS)
                );
        QuestionEntity newQuestion = this.questionRepository.save(new QuestionEntity(questionDTO.question(), entry));
        if (entry.getQuestions() == null)
            entry.setQuestions(new HashSet<>());
        entry.getQuestions().add(newQuestion);
        this.entryRepository.save(entry);
        return new SingleQuestionDTO(newQuestion.getId(), newQuestion.getQuestion());
    }

    public Optional<SingleQuestionDTO> getQuestion(String id) {
        return this.questionRepository.findById(id).map(mapToDto());
    }

    public static Function<QuestionEntity, SingleQuestionDTO> mapToDto() {
        return q -> {
            var dto = new SingleQuestionDTO(q.getId(), q.getQuestion());
            Link link = WebMvcLinkBuilder.linkTo(EntryController.class).slash(q.getParent().getId()).withRel("entry");
            dto.add(link);
            return dto;
        };
    }
}
