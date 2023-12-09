package pl.lechowicz.queansserver.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lechowicz.queansserver.dashboard.modelDTO.DashboardDTO;
import pl.lechowicz.queansserver.dashboard.service.DashboardService;

@RestController
@RequestMapping("/")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping()
    public ResponseEntity<DashboardDTO> getDashboard() {
        return ResponseEntity.ok(this.dashboardService.getDashboard());
    }
}
