package pl.lechowicz.queansserver.entry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@NoArgsConstructor
@Document
public class EntryEntity extends Entity{
    private Set<QuestionEntity> questions;

    private Set<AnswerEntity> answers;

    public EntryEntity(Set<QuestionEntity> questions, Set<AnswerEntity> answers) {
        this.questions = questions;
        this.answers = answers;
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
