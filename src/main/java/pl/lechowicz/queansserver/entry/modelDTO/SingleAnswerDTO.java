package pl.lechowicz.queansserver.entry.modelDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class SingleAnswerDTO extends RepresentationModel<SingleAnswerDTO> {
    public final String id;
    public final String answer;

    public SingleAnswerDTO(String id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleAnswerDTO that = (SingleAnswerDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer);
    }
}
