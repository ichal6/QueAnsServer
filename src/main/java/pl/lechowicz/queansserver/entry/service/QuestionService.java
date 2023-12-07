package pl.lechowicz.queansserver.entry.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lechowicz.queansserver.common.exception.ResourceNotFoundException;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;
import pl.lechowicz.queansserver.entry.modelDTO.NewQuestionDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleQuestionDTO;
import pl.lechowicz.queansserver.entry.repository.EntryRepository;
import pl.lechowicz.queansserver.entry.repository.QuestionRepository;

import java.util.HashSet;

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
                    .map( q -> new SingleQuestionDTO(q.getId(), q.getQuestion()))
                    .orElseThrow(()-> new ResourceNotFoundException(ResourceNotFoundException.Message.THE_SET_IS_EMPTY));
    }

    @Transactional
    public SingleQuestionDTO addQuestion(NewQuestionDTO questionDTO, String entryId) {
        QuestionEntity newQuestion = this.questionRepository.save(new QuestionEntity(questionDTO.question()));
        EntryEntity entry = this.entryRepository.findById(entryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(ResourceNotFoundException.Message.THE_ENTRY_IS_NOT_EXISTS)
                );
        if (entry.getQuestions() == null)
            entry.setQuestions(new HashSet<>());
        entry.getQuestions().add(newQuestion);
        this.entryRepository.save(entry);
        return new SingleQuestionDTO(newQuestion.getId(), newQuestion.getQuestion());
    }
}
