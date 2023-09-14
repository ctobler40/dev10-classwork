package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;

import java.util.List;

import static learn.solarfarm.TestHelper.MISSING_ID;
import static learn.solarfarm.TestHelper.makePanel;

public class SolarPanelRepositoryDouble implements SolarPanelRepository {

    @Override
    public List<SolarPanel> findBySection(String section) {
        if ("red".equals(section)) {
            return List.of(makePanel(1), makePanel(2), makePanel(3));
        }
        return List.of();
    }

    @Override
    public SolarPanel findByKey(String section, int row, int column) {
        if ("red".equals(section)) {
            return makePanel(row);
        }
        return null;
    }

    @Override
    public SolarPanel findById(int id) {
        return makePanel(id);
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) {
        return solarPanel;
    }

    @Override
    public boolean update(SolarPanel panel) {
        return MISSING_ID != panel.getId();
    }

    @Override
    public boolean deleteById(int id) {
        return MISSING_ID != id;
    }


}
