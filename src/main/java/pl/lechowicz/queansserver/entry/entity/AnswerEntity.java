package pl.lechowicz.queansserver.entry.entity;

import lombok.*;
import pl.lechowicz.queansserver.common.Entity;

@NoArgsConstructor
public class AnswerEntity extends Entity {
    private String answer;

    public AnswerEntity(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
