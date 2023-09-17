package learn.field_agent.data;

import learn.field_agent.data.mappers.AgentAliasMapper;
import learn.field_agent.models.AgentAlias;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

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
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("alias")
                .usingColumns("`name`", "persona", "agent_id")
                .usingGeneratedKeyColumns("alias_id");

        HashMap<String, Object> args = new HashMap<>();
        args.put("`name`", alias.getName());
        args.put("persona", alias.getPersona());
        args.put("agent_id", alias.getAgent().getAgentId());

        int aliasId = insert.executeAndReturnKey(args).intValue();
        alias.setAliasId(aliasId);
        return alias;
    }

    @Override
    public boolean update(AgentAlias alias) {
        final String sql = "update alias set " +
                "`name` = ?, " +
                "persona = ?, " +
                "agent_id = ?, " +
                "where alias_id = ?;";
        return jdbcTemplate.update(sql, alias.getName(), alias.getPersona(), alias.getAgent().getAgentId(), alias.getAliasId()) > 0;
    }

    @Override
    public boolean deleteById(int aliasId) {
        final String sql = "delete from alias where alias_id = ?;";
        return jdbcTemplate.update(sql, aliasId) > 0;
    }
}
