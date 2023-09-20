package learn.field_agent.data;

import learn.field_agent.data.mappers.MissionMapper;
import learn.field_agent.data.mappers.LocationMapper;
import learn.field_agent.models.Mission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

@Repository
public class MissionJdbcTemplateRepository implements MissionRepository {

    private final JdbcTemplate jdbcTemplate;

    public MissionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mission> findAll() {
        final String sql = "select " +
                "a.agency_id, a.short_name, a.long_name, " +
                "m.mission_id, m.code_name, m.notes, m.start_date, m.projected_end_date, m.actual_end_date, m.operational_cost, m.agency_id " +
                "from agency a " +
                "inner join mission m on a.agency_id = m.agency_id;";

        return jdbcTemplate.query(sql, new MissionMapper());
    }

    // NOTE: We only need transactional if we want to group multiple queries/operations as one SQL transaction, so that they wholly fail or wholly succeed.
    @Override
    public Mission findById(int missionId) {

        final String sql = "select " +
                "a.agency_id, a.short_name, a.long_name, " +
                "m.mission_id, m.code_name, m.notes, m.start_date, m.projected_end_date, m.actual_end_date, m.operational_cost, m.agency_id " +
                "from agency a " +
                "inner join mission m on a.agency_id = m.agency_id " +
                "where m.mission_id = ?;";

        return jdbcTemplate.query(sql, new MissionMapper(), missionId).stream()
                .findAny().orElse(null);
    }

    @Override
    public Mission add(Mission mission) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("mission")
                .usingColumns("code_name", "notes", "start_date", "projected_end_date", "actual_end_date", "operational_cost", "agency_id")
                .usingGeneratedKeyColumns("mission_id");

        HashMap<String, Object> args = new HashMap<>();
        args.put("code_name", mission.getCodeName());
        args.put("notes", mission.getNotes());
        args.put("start_date", mission.getStartDate());
        args.put("projected_end_date", mission.getEndDate_Projected());
        args.put("actual_end_date", mission.getEndDate_Actual());
        args.put("operational_cost", mission.getOperationalCost());
        args.put("agency_id", mission.getAgency().getAgencyId());

        int missionId = insert.executeAndReturnKey(args).intValue();
        mission.setMissionId(missionId);
        return mission;
    }

    @Override
    public boolean update(Mission mission) {

        final String sql = "update mission set "
                + "code_name = ?, "
                + "notes = ? "
                + "start_date = ?, "
                + "projected_end_date = ? "
                + "actual_end_date = ?, "
                + "operational_cost = ? "
                + "agency_id = ?, "
                + "where mission_id = ?";

        return jdbcTemplate.update(sql, mission.getCodeName(), mission.getNotes(), mission.getStartDate(), mission.getEndDate_Projected(),
                mission.getEndDate_Actual(), mission.getOperationalCost(), mission.getAgency().getAgencyId(), mission.getMissionId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int missionId) {
        return jdbcTemplate.update("delete from mission where mission_id = ?", missionId) > 0;
    }
}
