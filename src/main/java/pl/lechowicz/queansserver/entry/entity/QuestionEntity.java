package pl.lechowicz.queansserver.entry.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import pl.lechowicz.queansserver.common.Entity;

@Document
public class QuestionEntity extends Entity {
    private String question;

    public QuestionEntity(String question) {
        this.question = question;
    }

    public QuestionEntity() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
