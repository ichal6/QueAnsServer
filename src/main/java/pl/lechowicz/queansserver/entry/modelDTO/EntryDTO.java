package pl.lechowicz.queansserver.entry.modelDTO;

import java.util.Set;

public record EntryDTO(Set<String> questions, Set<String> answers) {
}
