package learn.solarfarm.ui;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelFileRepository;
import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.SolarPanel;

import java.util.List;

public class Controller {
    private final View view;
    private final SolarPanelService service;

    public Controller(View view, SolarPanelService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        view.displayHeader("Welcome to Solar Farm");
        try {
            runApp();
        } catch (DataAccessException ex) {
            view.displayErrors(List.of(ex.getMessage()));
        }
        view.displayMessage("Goodbye!");
    }

    private void runApp() throws DataAccessException {
        for (int option = view.chooseMenuOption();
             option > 0;
             option = view.chooseMenuOption()) {

            switch (option) {
                case 1:
                    findSolarPanelsBySection();
                    break;
                case 2:
                    addSolarPanel();
                    break;
                case 3:
                    updateSolarPanel();
                    break;
                case 4:
                    // TODO: complete delete! This is probably incorrect
                    deleteSolarPanelByKey();
                    break;
            }
        }
    }

    private void findSolarPanelsBySection() throws DataAccessException {
        view.displayHeader("Find Panels by Section");
        String section = view.getSection();
        List<SolarPanel> solarPanels = service.findBySection(section);
        if (solarPanels.isEmpty()) {
            view.displayMessage("There are no panels in this section.");
        } else {
            view.displaySolarPanels(section, solarPanels);
        }
    }

    private void addSolarPanel() throws DataAccessException {
        SolarPanel solarPanel = view.addSolarPanel();
        SolarPanelResult result = service.create(solarPanel);
        if (result.isSuccess()) {
            view.displayMessage("[Success]%nPanel %s added.", result.getSolarPanel().getKey());
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    private void updateSolarPanel() throws DataAccessException
    {
        view.displayHeader("Update a Panel");
        // Grab the section, row, and column from the view.
        String section = view.getSection();
        int row = view.getRow();
        int column = view.getColumn();

        // Use the service to fetch a solar panel by its key (section, row, column).
        SolarPanel solarPanel = service.findByKey(section, row, column);

        // Complete update
        if (solarPanel != null)
        {
            SolarPanel updatedSolarPanel = view.updateSolarPanel(solarPanel);
            SolarPanelResult result = service.update(updatedSolarPanel);
            if (result.isSuccess())
                view.displayMessage("[Success]\nSolar Panel " + updatedSolarPanel.getKey() + " successfully updated.");
            else
                view.displayErrors(result.getErrorMessages());
        }
        else
            view.displayMessage("[Err]\nSolar Panel " + section + "-" + row + "-" + column + " not found.");
    }

    private void deleteSolarPanelByKey() throws DataAccessException
    {
        // TODO: Complete!
        view.displayHeader("Remove Solar Panel");

        String section = view.getSection();
        int row = view.getRow();
        int column = view.getColumn();

        SolarPanel selectedSolarPanel = service.findByKey(section, row, column);

        SolarPanel solarPanel = view.selectSolarPanel(service.findAll(), selectedSolarPanel);
        if (solarPanel != null)
        {
            SolarPanelResult result = service.deleteByKey(solarPanel.getSection(), solarPanel.getRow(), solarPanel.getColumn());
            if (result.isSuccess())
                view.displayMessage("[Success]\nSolar Panel " + solarPanel.getKey() + " removed.");
            else
                view.displayMessage(result.getErrorMessages().get(0));
        }
        else
            view.displayMessage("[Err]\nSolar Panel " + section + "-" + row + "-" + column + " not found.");
    }
}
