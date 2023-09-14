package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SolarPanelMapper implements RowMapper<SolarPanel>
{
    // To map is to read a row from a database, instantiate a Java model, and set its fields with setter methods or a constructor.
    //A Spring JDBC mapper class must implement RowMapper<T>.
    // This guarantees a consistent approach. Here we implement the CampaignMapper class which has a hard requirement on the Campaign model.
    @Override
    public SolarPanel mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setId(rs.getInt("solar_panel_id"));
        solarPanel.setSection(rs.getString("section"));
        solarPanel.setRow(rs.getInt("row"));
        solarPanel.setColumn(rs.getInt("column"));
        solarPanel.setYearInstalled(rs.getInt("year_installed"));
        solarPanel.setMaterial(Material.valueOf(rs.getString("material")));
        solarPanel.setTracking((rs.getInt("is_tracking") == 1));   // solarPanel.setTracking((rs.getBoolean("is_tracking")));
        return solarPanel;
    }
}