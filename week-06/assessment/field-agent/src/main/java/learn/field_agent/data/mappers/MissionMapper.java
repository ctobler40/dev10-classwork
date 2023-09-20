package learn.field_agent.data.mappers;

import learn.field_agent.models.Agency;
import learn.field_agent.models.Mission;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MissionMapper implements RowMapper<Mission>
{
    @Override
    public Mission mapRow(ResultSet resultSet, int i) throws SQLException
    {
        Agency agency = new Agency();
        agency.setAgencyId(resultSet.getInt("agency_id"));
        agency.setShortName(resultSet.getString("short_name"));
        agency.setLongName(resultSet.getString("long_name"));

        Mission mission = new Mission();
        mission.setMissionId(resultSet.getInt("mission_id"));
        mission.setCodeName(resultSet.getString("code_name"));
        mission.setNotes(resultSet.getString("notes"));
        mission.setStartDate(resultSet.getDate("start_date").toLocalDate());
        mission.setEndDate_Projected(resultSet.getDate("projected_end_date").toLocalDate());
        mission.setEndDate_Actual(resultSet.getDate("actual_end_date").toLocalDate());
        mission.setOperationalCost(resultSet.getBigDecimal("operational_cost"));
        mission.setAgency(agency);
        return mission;
    }
}
