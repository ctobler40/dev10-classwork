package learn.solarfarm.data;
import learn.solarfarm.DataHelper;
import learn.solarfarm.models.SolarPanel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;

public class SolarPanelJdbcTemplateRepository implements SolarPanelRepository {

    // Each repository is dependent on a JdbcTemplate instance. We make the JdbcTemplate field final and require it in the constructor.
    // This guarantees that the field is set. (It doesn't guarantee that the field can't be null.)

    private final JdbcTemplate jdbcTemplate;

    public SolarPanelJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SolarPanel findById(int id) throws DataAccessException
    {
        // To return a single SolarPanel, stream the list, find its first item, then return the item if it's present or else return null.
        final String sql =
                """
                select * from solar_panel
                where solar_panel_id = ?;
                """;

        return jdbcTemplate.query(sql, new SolarPanelMapper(), id)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException
    {
        final String sql =
                """
                select * from solar_panel
                where section = ?;
                """;

        return jdbcTemplate.query(sql, new SolarPanelMapper(), section);
    }

    @Override
    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException
    {
        final String sql =
                """
                select * from solar_panel
                where section = ? and
                    `row` = ? and
                    `column` = ?;
                """;

        return jdbcTemplate.query(sql, new SolarPanelMapper(), section, row, column)
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<SolarPanel> findAll() throws DataAccessException
    {
        final String sql =
                """
                select * from solar_panel;
                """;

        return jdbcTemplate.query(sql, new SolarPanelMapper());
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException
    {
        // To use SimpleJdbcInsert:
        // 1. Instantiate it with a JdbcTemplate instance.
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                // 2. Set the table name, campaign. The withTableName method represents a fluent interface.
                // These are methods that support chaining and always return an instance of the current object.
                .withTableName("solar_panel")
                // 2.5. We need to tell it what the column names are as well
                .usingColumns("section", "`row`", "`column`", "year_installed", "material", "is_tracking")
                // 3. Explicitly define the auto-incremented key, campaign_id.
                // The usingGeneratedKeyColumns is also a fluent interface. The key column must match.
                .usingGeneratedKeyColumns("solar_panel_id");

        // 4. Create an argument hash map. The key should be a String and the value an Object.
        // The key labels the column and the value has flexibility in its data type.
        // SimpleJdbcInsert will decide how to set the type.
        HashMap<String, Object> args = new HashMap<>();
        // 5. Add keys and values to the argument map.
        // The key is the column name.
        // The value is the appropriate campaign property.
        // Don't leave out a column expect the auto-incremented key.
        args.put("section", solarPanel.getSection());
        args.put("`row`", solarPanel.getRow());
        args.put("`column`", solarPanel.getColumn());
        args.put("year_installed", solarPanel.getYearInstalled());
        args.put("material", solarPanel.getMaterial());
        args.put("is_tracking", solarPanel.isTracking());   // This may need to return an int and not a string

        // 6. insert.executeAndReturnKeys(args) builds the SQL query, merges arguments, and sends it to the database server.
        // On success, the auto-incremented key is returned.
        // We can capture it with the campaignId variable.
        int solarPanelId = insert.executeAndReturnKey(args).intValue();
        // 7. Set the campaign's new id and return it.
        solarPanel.setId(solarPanelId);
        return solarPanel;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException
    {
        // JdbcTemplate.update can be used for inserts, updates, and deletes.
        // It returns the number of rows affected.
        // In this case, if a campaign id was not found, the rows affected would be zero and the update expression would return false.
        // If the campaign id was found, rows affected would be greater than zero and the update expression would return true.
        final String sql = """
                update solar_panel set
                    section = ?
                    `row` = ?,
                    `column` = ?,
                    year_installed = ?,
                    material = ?,
                    is_tracking = ?
                where solar_panel_id = ?;
                """;

        return jdbcTemplate.update(sql,
                solarPanel.getSection(),
                solarPanel.getRow(),
                solarPanel.getColumn(),
                solarPanel.getYearInstalled(),
                solarPanel.getMaterial().toString(),
                Integer.parseInt(Boolean.toString(solarPanel.isTracking())),   // Confirm if this is working!
                solarPanel.getId()) > 0;
    }

    @Override
    public boolean deleteByKey(SolarPanel solarPanel) throws DataAccessException
    {
        return jdbcTemplate.update("delete from solar_panel where solar_panel_id = ?;", solarPanel.getId()) > 0;
    }

}
