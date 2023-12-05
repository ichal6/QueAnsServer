package pl.lechowicz.queansserver.entry.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.lechowicz.queansserver.common.Entity;

import java.util.Set;

@Document
public class EntryEntity extends Entity {
    @DBRef
    private Set<QuestionEntity> questions;

    @DBRef
    private Set<AnswerEntity> answers;

    public EntryEntity(Set<QuestionEntity> questions, Set<AnswerEntity> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public EntryEntity() {
    }

    public Set<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionEntity> questions) {
        this.questions = questions;
    }

    public Set<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerEntity> answers) {
        this.answers = answers;
    }
}
