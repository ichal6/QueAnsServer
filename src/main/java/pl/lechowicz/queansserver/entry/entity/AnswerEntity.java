package pl.lechowicz.queansserver.entry.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import pl.lechowicz.queansserver.common.entity.Entity;

public class AnswerEntity extends Entity {
    private String answer;
    @DBRef
    private EntryEntity parent;

    public AnswerEntity(String answer, EntryEntity entry) {
        this.answer = answer;
        this.parent = entry;
    }

    public AnswerEntity() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public EntryEntity getParent() {
        return parent;
    }

    public void setParent(EntryEntity parent) {
        this.parent = parent;
    }
}
