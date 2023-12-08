package pl.lechowicz.queansserver.entry.modelDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class SingleQuestionDTO extends RepresentationModel<SingleQuestionDTO> {
    public final String id;
    public final String question;

    public SingleQuestionDTO(String id, String question) {
        this.id = id;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleQuestionDTO that = (SingleQuestionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question);
    }
}
