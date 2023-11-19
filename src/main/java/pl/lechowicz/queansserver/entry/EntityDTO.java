package pl.lechowicz.queansserver.entry;

import java.util.Set;

public record EntityDTO(Set<String> questions, Set<String> answers) {
}
