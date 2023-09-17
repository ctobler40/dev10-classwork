package learn.field_agent.data.mappers;

import learn.field_agent.models.Agency;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AliasMapper implements RowMapper<Alias>
{
    @Override
    public Alias mapRow(ResultSet resultSet, int i) throws SQLException
    {
        Agent agent = new Agent();
        agent.setAgentId(resultSet.getInt("agent_id"));
        agent.setFirstName(resultSet.getString("first_name"));
        agent.setMiddleName(resultSet.getString("middle_name"));
        agent.setLastName(resultSet.getString("last_name"));
        if (resultSet.getDate("dob") != null) {
            agent.setDob(resultSet.getDate("dob").toLocalDate());
        }
        agent.setHeightInInches(resultSet.getInt("height_in_inches"));

        Alias alias = new Alias();
        alias.setAliasId(resultSet.getInt("alias_id"));
        alias.setName(resultSet.getString("`name`"));
        alias.setPersona(resultSet.getString("persona"));
        alias.setAgent(agent);
        return alias;
    }
}
