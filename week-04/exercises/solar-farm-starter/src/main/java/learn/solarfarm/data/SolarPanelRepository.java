package learn.solarfarm.data;

import learn.solarfarm.models.SolarPanel;

import javax.xml.crypto.Data;
import java.util.List;

public interface SolarPanelRepository {
    SolarPanel findById(int id) throws DataAccessException;
    List<SolarPanel> findBySection(String section) throws DataAccessException;

    SolarPanel findByKey(String section, int row, int column) throws DataAccessException;
    List<SolarPanel> findAll() throws DataAccessException;

    SolarPanel create(SolarPanel solarPanel) throws DataAccessException;

    // Add an update method
    boolean update(SolarPanel solarPanel) throws DataAccessException;

    // Add a delete method
    boolean deleteByKey(SolarPanel solarPanel) throws DataAccessException;
}
