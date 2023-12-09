package pl.lechowicz.queansserver.entry.service;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lechowicz.queansserver.common.exception.ResourceNotFoundException;
import pl.lechowicz.queansserver.entry.controller.EntryController;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.modelDTO.NewAnswerDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleAnswerDTO;
import pl.lechowicz.queansserver.entry.repository.AnswerRepository;
import pl.lechowicz.queansserver.entry.repository.EntryRepository;

import java.util.HashSet;
import java.util.function.Function;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final EntryRepository entryRepository;

    public AnswerService(AnswerRepository answerRepository, EntryRepository entryRepository) {
        this.answerRepository = answerRepository;
        this.entryRepository = entryRepository;
    }

    @Transactional
    public SingleAnswerDTO addAnswer(NewAnswerDTO answerDTO, String entryId) {
        EntryEntity entry = this.entryRepository.findById(entryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(ResourceNotFoundException.Message.THE_ENTRY_IS_NOT_EXISTS)
                );
        if (entry.getAnswers() == null)
            entry.setAnswers(new HashSet<>());
        AnswerEntity newAnswer = this.answerRepository.save(new AnswerEntity(answerDTO.answer(), entry));
        entry.getAnswers().add(newAnswer);
        this.entryRepository.save(entry);
        return new SingleAnswerDTO(newAnswer.getId(), newAnswer.getAnswer());
    }

    public static Function<AnswerEntity, SingleAnswerDTO> mapToDto() {
        return answerEntity -> {
            var dto = new SingleAnswerDTO(answerEntity.getId(), answerEntity.getAnswer());
            Link link = WebMvcLinkBuilder.linkTo(EntryController.class).slash(answerEntity.getParent().getId()).withRel("entry");
            dto.add(link);
            return dto;
        };
    }
}
