package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;

import java.util.List;

public interface SolarPanelRepository {
    List<SolarPanel> findBySection(String section);

    SolarPanel findByKey(String section, int row, int column);

    SolarPanel findById(int id);

    SolarPanel create(SolarPanel solarPanel);

    boolean update(SolarPanel panel);

    boolean deleteById(int id);
}
