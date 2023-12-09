package pl.lechowicz.queansserver.dashboard.service;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import pl.lechowicz.queansserver.dashboard.modelDTO.DashboardDTO;
import pl.lechowicz.queansserver.entry.controller.EntryController;
import pl.lechowicz.queansserver.entry.service.QuestionService;

@Service
public class DashboardService {
    private final QuestionService questionService;

    public DashboardService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public DashboardDTO getDashboard() {
        DashboardDTO dashboardDTO = new DashboardDTO(this.questionService.getRandomQuestion());
        Link linkToEntry = WebMvcLinkBuilder.linkTo(EntryController.class).slash("/").withRel("all-entry");
        dashboardDTO.add(linkToEntry);

        return dashboardDTO;
    }
}
