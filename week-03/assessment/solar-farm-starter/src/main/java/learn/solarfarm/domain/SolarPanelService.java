package learn.solarfarm.domain;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import learn.solarfarm.ui.ConsoleIO;
import learn.solarfarm.ui.View;

import java.time.Year;
import java.util.List;
import java.util.Map;

public class SolarPanelService {
    public final static int MAX_ROW_COLUMN = 250;
    public static boolean blankInput = false;

    private final SolarPanelRepository repository;

    public SolarPanelService(SolarPanelRepository repository) {
        this.repository = repository;
    }

    public static int getMaxInstallationYear() {
        return Year.now().getValue();
    }

    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        return repository.findBySection(section);
    }

    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException {
        return repository.findByKey(section, row, column);
    }

    public SolarPanelResult create(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = validate(solarPanel);

        if (solarPanel != null && solarPanel.getId() > 0) {
            result.addErrorMessage("SolarPanel `id` should not be set.");
        }

        if (result.isSuccess()) {
            solarPanel = repository.create(solarPanel);
            result.setSolarPanel(solarPanel);
        }

        return result;
    }

    public SolarPanelResult update(SolarPanel solarPanel) throws DataAccessException
    {
        SolarPanelResult result = validate(solarPanel);
        ConsoleIO console = new ConsoleIO();

        if (solarPanel == null)
            console.println("\n[Err]\nThere is no panel in that area.");

        // Do not run if any of the validations do not pass
        if (!result.isSuccess()) {
            return result;
        }
        // Do not run if the ID is <= 0
        if (solarPanel.getId() <= 0) {
            result.addErrorMessage("solar panel id is required");
            return result;
        }
        // If we make it here, then we have successfully gotten passed every check.
        // Now we actually update the solar panel
        console.println("\nEditing " + solarPanel.getSection() + "-" + solarPanel.getRow() + "-" + solarPanel.getColumn() + "\n" +
                      "Press [Enter] to keep original value.\n");
        SolarPanel newSolarPanel = new SolarPanel(
            solarPanel.getId(),   // Keep it's id
            console.readString("Section (" + solarPanel.getSection() + ")"),
            console.readInt("Row (" + solarPanel.getRow() + ")", 0, 100),
            console.readInt("Column (" + solarPanel.getColumn() + ")", 0, 100),
            console.readInt("Installation Year (" + solarPanel.getYearInstalled() + ")", 1900, 2023),
            getMaterialFromInput(console, solarPanel),
            console.readBoolean("Tracked (" + (solarPanel.isTracking() ? "yes" : "no") + ") [y/n]")
        );
        solarPanel = setNewSolarPanelValues(newSolarPanel, solarPanel);
        // Here we are writing the solar panel data to the File
        boolean success = repository.update(solarPanel);
        if (!success) {
            result.addErrorMessage("could not update solar panel id " + solarPanel.getId());
        }
        // Print the new success line
        console.println("\n[Success]\nPanel " + solarPanel.getSection() + "-" + solarPanel.getRow() + "-" + solarPanel.getColumn() + " updated.");
        return result;
    }

    public SolarPanel setNewSolarPanelValues(SolarPanel newSolarPanel, SolarPanel solarPanel)
    {
        if (newSolarPanel.getSection().equalsIgnoreCase(""))
            newSolarPanel.setSection(solarPanel.getSection());
        if (newSolarPanel.getRow() == -1)
            newSolarPanel.setRow(solarPanel.getRow());
        if (newSolarPanel.getColumn() == -1)
            newSolarPanel.setColumn(solarPanel.getColumn());
        if (newSolarPanel.getYearInstalled() == -1)
            newSolarPanel.setYearInstalled(solarPanel.getYearInstalled());
        if (newSolarPanel.getMaterial() == Material.NO_MATERIAL)
            newSolarPanel.setMaterial(solarPanel.getMaterial());
        if (blankInput)
            newSolarPanel.setTracking(solarPanel.isTracking());
        System.out.println("\n" + newSolarPanel.isTracking() + "\n");
        return newSolarPanel;
    }

    public Material getMaterialFromInput(ConsoleIO console, SolarPanel solarPanel)
    {
        Material material = Material.NO_MATERIAL;
        try
        {
            material = Material.valueOf(console.readString("Material (" + solarPanel.getMaterial() + ")"));
        }
        catch (IllegalArgumentException ex)
        {
            // Nothing
        }
        return material;
    }

    // TODO: Add a delete method (possibly deleteById?)
    public SolarPanelResult deleteByKey(String section, int row, int column) throws DataAccessException
    {
        SolarPanelResult result = new SolarPanelResult();
        SolarPanel solarPanel = findByKey(section, row, column);
        ConsoleIO console = new ConsoleIO();
        // Check if the solarPanel returns null. If so, then display no panel error message
        if (solarPanel == null)
            console.println("\n[Err]\nThere is no panel " + section + "-" + row + "-" + column + ".");
        else
        {
            // Remove it here

            console.println("\n[Success]\nPanel " + solarPanel.getSection() + "-" + solarPanel.getRow() + "-" + solarPanel.getColumn() + " removed.");
        }
        // This checks if it is successfully removed from the repository file
        boolean success = repository.deleteByKey(solarPanel);
        if (!success)
            result.addErrorMessage("\n[Err]\nDid not remove from repository.");
        return result;
    }

    private SolarPanelResult validate(SolarPanel solarPanel) throws DataAccessException {
        SolarPanelResult result = new SolarPanelResult();

        if (solarPanel == null) {
            result.addErrorMessage("SolarPanel cannot be null.");
            return result;
        }

        if (solarPanel.getSection() == null || solarPanel.getSection().isBlank()) {
            result.addErrorMessage("SolarPanel `section` is required.");
        }

        if (solarPanel.getRow() < 1 || solarPanel.getRow() >= MAX_ROW_COLUMN) {
            result.addErrorMessage("SolarPanel `row` must be a positive number less than or equal to %s.", MAX_ROW_COLUMN);
        }

        if (solarPanel.getColumn() < 1 || solarPanel.getColumn() >= MAX_ROW_COLUMN) {
            result.addErrorMessage("SolarPanel `column` must be a positive number less than or equal to %s.", MAX_ROW_COLUMN);
        }

        if (solarPanel.getYearInstalled() > getMaxInstallationYear()) {
            result.addErrorMessage("SolarPanel `yearInstalled` must be in the past.");
        }

        if (solarPanel.getMaterial() == null) {
            result.addErrorMessage("SolarPanel `material` is required.");
        }

        // If everything is successful so far, then check if the combined values
        // of **Section**, **Row**, and **Column** are unique (i.e. the natural key).
        if (result.isSuccess()) {
            SolarPanel existingSolarPanel = repository.findByKey(solarPanel.getSection(),
                    solarPanel.getRow(), solarPanel.getColumn());

            // If an existing panel was found for the provided **Section**, **Row**, and **Column** values
            // add an error message if the id values don't match (i.e. they're not the same record).
            if (existingSolarPanel != null && existingSolarPanel.getId() != solarPanel.getId()) {
                result.addErrorMessage("SolarPanel `section`, `row`, and `column` must be unique.");
            }
        }

        return result;
    }
}
