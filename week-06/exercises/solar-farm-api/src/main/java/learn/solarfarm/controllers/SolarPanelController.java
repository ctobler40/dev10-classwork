package learn.solarfarm.controllers;

import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.SolarPanel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 7. Add a solar panel controller. Annotate it with @RestController.
@RestController
public class SolarPanelController
{
    // 9. Add a private final service field in the controller and add an appropriate constructor.
    private final SolarPanelService service;

    public SolarPanelController(SolarPanelService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home()
    {
        return "Hello world.";
    }

    // 10. Start with a "read" operation or GET method in the controller. Use appropriate mappings/URLs. Find panels per section.
    @GetMapping("/panels/{section}")
    public List<SolarPanel> findPanelsPerSection(@PathVariable String section)
    {
        return service.findBySection(section);
    }

    // 12. Add a panel in the controller.
    @PutMapping("/panels/{section}/{row}/{column}")
    public void addAllPanels(@PathVariable String section, @PathVariable int row, @PathVariable int col)
    {

    }

    // 14. Update a panel in the controller.

    // 16. Delete a panel in the controller.

}
