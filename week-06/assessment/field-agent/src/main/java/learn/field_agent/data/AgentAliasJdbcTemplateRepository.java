package learn.field_agent.data;

import learn.field_agent.data.mappers.AgentAliasMapper;
import learn.field_agent.models.AgentAlias;
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
import java.util.Objects;

@Repository
public class AgentAliasJdbcTemplateRepository implements AgentAliasRepository {

    private final JdbcTemplate jdbcTemplate;

    public AgentAliasJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<AgentAlias> findAll() {
        final String sql = "select a.agent_id, a.first_name, a.middle_name, a.last_name, a.dob, a.height_in_inches, " +
                "al.alias_id, al.`name`, al.persona, al.agent_id " +
                "from agent a " +
                "inner join alias al on a.agent_id = al.agent_id;";

        return jdbcTemplate.query(sql, new AgentAliasMapper());
    }

    @Override
    @Transactional
    public AgentAlias findById(int aliasId) {
        final String sql = "select a.agent_id, a.first_name, a.middle_name, a.last_name, a.dob, a.height_in_inches, " +
                "al.alias_id, al.name, al.persona, al.agent_id " +
                "from agent a " +
                "inner join alias al on a.agent_id = al.agent_id " +
                "where al.alias_id = ?;";

        return jdbcTemplate.query(sql, new AgentAliasMapper(), aliasId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<AgentAlias> findByName(String name) {
        final String sql = "select a.agent_id, a.first_name, a.middle_name, a.last_name, a.dob, a.height_in_inches, " +
                "al.alias_id, al.name, al.persona, al.agent_id " +
                "from agent a " +
                "inner join alias al on a.agent_id = al.agent_id " +
                "where al.name = ?;";

        return jdbcTemplate.query(sql, new AgentAliasMapper(), name);
    }
    @Override
    public AgentAlias add(AgentAlias alias) {
        final String sql = "insert into alias (`name`, persona, agent_id) values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alias.getName());
            ps.setString(2, alias.getPersona());
            ps.setInt(3, alias.getAgentId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        alias.setAliasId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return alias;
    }

    @Override
    public boolean update(AgentAlias alias) {
        final String sql = "update alias set " +
                "`name` = ?, " +
                "persona = ?, " +
                "agent_id = ?, " +
                "where alias_id = ?;";
        return jdbcTemplate.update(sql, alias.getName(), alias.getPersona(), alias.getAgentId(), alias.getAliasId()) > 0;
    }

    @Override
    public boolean deleteById(int aliasId) {
        final String sql = "delete from alias where alias_id = ?;";
        return jdbcTemplate.update(sql, aliasId) > 0;
    }
}
