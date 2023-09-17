package learn.field_agent.data.mappers;

import learn.field_agent.models.Agency;
import learn.field_agent.models.Agent;
import learn.field_agent.models.AgentAlias;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgentAliasMapper implements RowMapper<AgentAlias>
{
    @Override
    public AgentAlias mapRow(ResultSet resultSet, int i) throws SQLException
    {
        AgentAlias alias = new AgentAlias();
        alias.setAliasId(resultSet.getInt("alias_id"));
        alias.setName(resultSet.getString("`name`"));
        alias.setPersona(resultSet.getString("persona"));
        alias.setAgentId(resultSet.getInt("agent_id"));
        return alias;
    }
}