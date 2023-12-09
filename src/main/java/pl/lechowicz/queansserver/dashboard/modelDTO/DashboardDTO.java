package pl.lechowicz.queansserver.dashboard.modelDTO;

import org.springframework.hateoas.RepresentationModel;
import pl.lechowicz.queansserver.entry.modelDTO.EntryDTO;
import pl.lechowicz.queansserver.entry.modelDTO.SingleQuestionDTO;

import java.util.Objects;

public class DashboardDTO extends RepresentationModel<EntryDTO> {
    private final SingleQuestionDTO randomQuestion;

    public DashboardDTO(SingleQuestionDTO question) {
        this.randomQuestion = question;
    }

    public SingleQuestionDTO getRandomQuestion() {
        return randomQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DashboardDTO that = (DashboardDTO) o;
        return Objects.equals(randomQuestion, that.randomQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), randomQuestion);
    }
}

