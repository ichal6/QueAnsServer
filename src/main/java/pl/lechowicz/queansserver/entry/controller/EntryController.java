package pl.lechowicz.queansserver.entry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lechowicz.queansserver.entry.repository.EntryRepository;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;
import pl.lechowicz.queansserver.entry.modelDTO.EntryDTO;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entry")
public class EntryController {
    private final EntryRepository entryRepository;
    public EntryController(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @GetMapping
    public List<EntryDTO> getAll() {
        return this.entryRepository.findAll().stream()
                .peek(e -> {
                    if (e.getQuestions() == null)
                        e.setQuestions(new HashSet<>());
                    if (e.getAnswers() == null)
                        e.setAnswers(new HashSet<>());
                })
                .map(x -> new EntryDTO(
                        x.getQuestions().stream().map(QuestionEntity::getQuestion)
                                .collect(Collectors.toSet()),
                        x.getAnswers().stream().map(AnswerEntity::getAnswer)
                                .collect(Collectors.toSet())
                )).toList();
    }

    @PostMapping()
    public ResponseEntity<String> addEntry() {
        return ResponseEntity.ok(this.entryRepository.save(new EntryEntity()).getId());
    }
}
