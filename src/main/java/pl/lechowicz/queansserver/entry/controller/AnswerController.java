package pl.lechowicz.queansserver.entry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lechowicz.queansserver.entry.modelDTO.NewAnswerDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleAnswerDTO;
import pl.lechowicz.queansserver.entry.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{entryId}")
    public ResponseEntity<SingleAnswerDTO> insertQuestion(
            @RequestBody NewAnswerDTO answerDTO, @PathVariable("entryId") String entryId) {
        return ResponseEntity.ok(this.answerService.addAnswer(answerDTO, entryId));
    }
}
