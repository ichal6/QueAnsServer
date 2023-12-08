package pl.lechowicz.queansserver.entry.entity;

import pl.lechowicz.queansserver.common.entity.Entity;

public class AnswerEntity extends Entity {
    private String answer;

    public AnswerEntity(String answer) {
        this.answer = answer;
    }

    public AnswerEntity() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
