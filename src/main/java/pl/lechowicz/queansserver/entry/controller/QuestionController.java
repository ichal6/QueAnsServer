package pl.lechowicz.queansserver.entry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lechowicz.queansserver.entry.modelDTO.NewQuestionDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleQuestionDTO;
import pl.lechowicz.queansserver.entry.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/random")
    public ResponseEntity<SingleQuestionDTO> getRandomSingleQuestion() {
        return ResponseEntity.ok(this.questionService.getRandomQuestion());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SingleQuestionDTO> getQuestion(@PathVariable String id) {
        return ResponseEntity.of(this.questionService.getQuestion(id));
    }

    @PostMapping("/{entryId}")
    public ResponseEntity<SingleQuestionDTO> insertQuestion(@RequestBody NewQuestionDTO questionDTO, @PathVariable("entryId") String entryId) {
        return ResponseEntity.ok(this.questionService.addQuestion(questionDTO, entryId));
    }
}
