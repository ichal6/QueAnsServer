package pl.lechowicz.queansserver.entry.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.lechowicz.queansserver.common.entity.Entity;

@Document
public class QuestionEntity extends Entity {
    private String question;
    @DBRef
    private EntryEntity parent;

    public QuestionEntity(String question, EntryEntity entry) {
        this.question = question;
        this.parent = entry;
    }

    public QuestionEntity() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public EntryEntity getParent() {
        return parent;
    }

    public void setParent(EntryEntity parent) {
        this.parent = parent;
    }
}
