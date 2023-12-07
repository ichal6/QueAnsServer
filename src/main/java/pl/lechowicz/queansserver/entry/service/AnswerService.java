package pl.lechowicz.queansserver.entry.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lechowicz.queansserver.common.exception.ResourceNotFoundException;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.modelDTO.NewAnswerDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleAnswerDTO;
import pl.lechowicz.queansserver.entry.repository.AnswerRepository;
import pl.lechowicz.queansserver.entry.repository.EntryRepository;

import java.util.HashSet;

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
        AnswerEntity newAnswer = this.answerRepository.save(new AnswerEntity(answerDTO.answer()));
        EntryEntity entry = this.entryRepository.findById(entryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(ResourceNotFoundException.Message.THE_ENTRY_IS_NOT_EXISTS)
                );
        if (entry.getAnswers() == null)
            entry.setAnswers(new HashSet<>());
        entry.getAnswers().add(newAnswer);
        this.entryRepository.save(entry);
        return new SingleAnswerDTO(newAnswer.getId(), newAnswer.getAnswer());
    }
}
