package learn.solarfarm.ui;

import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;

import java.util.List;

public class View {
    private final TextIO io;
    public static boolean blankInput = false;

    public View(TextIO io) {
        this.io = io;
    }

    public int chooseMenuOption() {
        displayHeader("Main Menu");
        io.println("0. Exit");
        io.println("1. Find Panels by Section");
        io.println("2. Add a Panel");
        io.println("3. Update a Panel");
        io.println("4. Remove a Panel");
        return io.readInt("Choose [0-4]", 0, 4);
    }

    public String getSection() {
        io.println("");
        return io.readRequiredString("Section Name");
    }

    public int getRow() {
        return io.readInt("Row", 1, SolarPanelService.MAX_ROW_COLUMN);
    }

    public int getColumn() {
        return io.readInt("Column", 1, SolarPanelService.MAX_ROW_COLUMN);
    }

    public void displaySolarPanels(String section, List<SolarPanel> solarPanels) {
        io.println("");
        io.printf("Panels in %s%n", section);
        io.println("Row Col Year Material Tracking");
        for (SolarPanel sp : solarPanels) {
            io.printf("%3s %3s %4s %8s %8s%n", sp.getRow(), sp.getColumn(), sp.getYearInstalled(),
                    sp.getMaterial(), sp.isTracking() ? "yes" : "no");
        }
    }


    public void displayHeader(String message) {
        int length = message.length();
        io.println("");
        io.println(message);
        io.println("=".repeat(length));
    }

    public void displayErrors(List<String> errors) {
        displayMessage("[Errors]");
        for (String error : errors) {
            io.println(error);
        }
    }

    public void displayMessage(String message) {
        io.println("");
        io.println(message);
    }

    public void displayMessage(String format, Object... args) {
        displayMessage(String.format(format, args));
    }

    public SolarPanel addSolarPanel()
    {
        displayHeader("Add a Panel");
        io.println("");

        SolarPanel result = new SolarPanel();
        result.setSection(io.readRequiredString("Section"));
        result.setRow(io.readInt("Row", 1, SolarPanelService.MAX_ROW_COLUMN));
        result.setColumn(io.readInt("Column", 1, SolarPanelService.MAX_ROW_COLUMN));
        result.setMaterial(io.readEnum("Material", Material.class));
        result.setYearInstalled(io.readInt("Installation Year", SolarPanelService.getMaxInstallationYear()));
        result.setTracking(io.readBoolean("Tracked [y/n]"));

        return result;
    }

    public SolarPanel updateSolarPanel(SolarPanel solarPanel)
    {
        io.println("");
        displayHeader("Updating Solar Panel " + solarPanel.getKey());
        io.println("");

        SolarPanel newSolarPanel = new SolarPanel(
            solarPanel.getId(),   // Keep it's id
            io.readString("Section (" + solarPanel.getSection() + ")"),
            io.readInt("Row (" + solarPanel.getRow() + ")", 0, 100),
            io.readInt("Column (" + solarPanel.getColumn() + ")", 0, 100),
            io.readInt("Installation Year (" + solarPanel.getYearInstalled() + ")", 1900, 2023),
            getMaterialFromInput(solarPanel),
            io.readBoolean("Tracked (" + (solarPanel.isTracking() ? "yes" : "no") + ") [y/n]")
        );
        solarPanel = setNewSolarPanelValues(newSolarPanel, solarPanel);
        return solarPanel;
    }

    public SolarPanel selectSolarPanel(List<SolarPanel> all, SolarPanel selectedSolarPanel)
    {
        for (SolarPanel solarPanel : all)
        {
            if (solarPanel.isMatch(selectedSolarPanel))
            {
                selectedSolarPanel = solarPanel;
                break;
            }
        }

        if (selectedSolarPanel == null) {
            displayMessage("Solar Panel is not found.");
        }

        return selectedSolarPanel;
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
        return newSolarPanel;
    }

    public Material getMaterialFromInput(SolarPanel solarPanel)
    {
        Material material = Material.NO_MATERIAL;
        try
        {
            material = Material.valueOf(io.readString("Material (" + solarPanel.getMaterial() + ")"));
        }
        catch (IllegalArgumentException ex)
        {
            // Nothing
        }
        return material;
    }
}
