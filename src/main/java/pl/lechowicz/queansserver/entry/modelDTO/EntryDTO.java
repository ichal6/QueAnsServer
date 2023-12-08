package pl.lechowicz.queansserver.entry.modelDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.Set;

public class EntryDTO extends RepresentationModel<EntryDTO> {
    private final Set<String> questions;
    private final Set<String> answers;

    public EntryDTO(Set<String> questions, Set<String> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public Set<String> getQuestions() {
        return questions;
    }

    public Set<String> getAnswers() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntryDTO entryDTO = (EntryDTO) o;
        return Objects.equals(questions, entryDTO.questions) && Objects.equals(answers, entryDTO.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), questions, answers);
    }
}
