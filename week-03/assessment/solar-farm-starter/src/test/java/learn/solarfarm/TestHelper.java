package learn.solarfarm;

import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;

public class TestHelper
{
    public static final int VALID_ID = 1;

    public static SolarPanel makeSolarPanel(int id)
    {
        return new SolarPanel(
                id,
                String.format("Title #%s", id),
                id + 1,
                id + 2,
                2015,
                Material.CIGS,
                false
        );
    }

    public static SolarPanelResult makeResult(String message, SolarPanel solarPanel)
    {
        SolarPanelResult result = new SolarPanelResult();
        if(message != null)
        {
            result.addErrorMessage(message);
        }
        result.setSolarPanel(solarPanel);
        return result;
    }
}
