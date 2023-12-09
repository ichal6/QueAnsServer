package pl.lechowicz.queansserver.entry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lechowicz.queansserver.entry.modelDTO.SingleAnswerDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleQuestionDTO;
import pl.lechowicz.queansserver.entry.modelDTO.EntryDTO;
import pl.lechowicz.queansserver.entry.service.EntryService;

import java.util.Set;

@RestController
@RequestMapping("/api/entry")
public class EntryController {
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public ResponseEntity<Set<EntryDTO>> getAll() {
        return ResponseEntity.ok(this.entryService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryDTO> getEntry(@PathVariable String id) {
        return ResponseEntity.of(this.entryService.getSingleEntry(id));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<Set<SingleQuestionDTO>> getQuestions(@PathVariable("id") String entryId) {
        return ResponseEntity.ok(this.entryService.getQuestionsForEntry(entryId));
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<Set<SingleAnswerDTO>> getAnswers(@PathVariable("id") String entryId) {
        return ResponseEntity.ok(this.entryService.getAnswersForEntry(entryId));
    }

    @PostMapping()
    public ResponseEntity<String> addEntry() {
        return ResponseEntity.ok(this.entryService.newEntry());
    }
}
