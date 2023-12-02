package pl.lechowicz.queansserver.entry.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.lechowicz.queansserver.common.Entity;

@Document
@NoArgsConstructor
public class QuestionEntity extends Entity {
    private String question;

    public QuestionEntity(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
