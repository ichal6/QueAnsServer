package pl.lechowicz.queansserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.lechowicz.queansserver.entry.entity.AnswerEntity;
import pl.lechowicz.queansserver.entry.entity.EntryEntity;
import pl.lechowicz.queansserver.entry.entity.QuestionEntity;

import java.util.Set;

@SpringBootApplication
public class QueAnsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueAnsServerApplication.class, args);
    }

}
