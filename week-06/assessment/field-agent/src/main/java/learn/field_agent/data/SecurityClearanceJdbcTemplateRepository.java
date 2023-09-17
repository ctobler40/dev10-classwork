package learn.field_agent.data;

import learn.field_agent.data.mappers.SecurityClearanceMapper;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

@Repository
public class SecurityClearanceJdbcTemplateRepository implements SecurityClearanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public SecurityClearanceJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SecurityClearance> findAll()
    {
        final String sql = "select security_clearance_id, name as security_clearance_name from security_clearance;";

        return jdbcTemplate.query(sql, new SecurityClearanceMapper());
    }

    @Override
    public SecurityClearance findById(int securityClearanceId) {

        final String sql = "select security_clearance_id, name as security_clearance_name from security_clearance where security_clearance_id = ?; ";

        return jdbcTemplate.query(sql, new SecurityClearanceMapper(), securityClearanceId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public SecurityClearance add(SecurityClearance sc)
    {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("security_clearance")
                .usingColumns("`name`")
                .usingGeneratedKeyColumns("security_clearance_id");

        HashMap<String, Object> args = new HashMap<>();
        args.put("`name`", sc.getName());

        int scId = insert.executeAndReturnKey(args).intValue();
        sc.setSecurityClearanceId(scId);

        return sc;
    }

    @Override
    public int agentCount(int scId)
    {
        return jdbcTemplate.queryForObject("select count(*) from agency_agent where security_clearance_id = ?;", Integer.class, scId);
    }

    @Override
    public boolean update(SecurityClearance sc)
    {
        final String sql = "update security_clearance set `name` = ? where security_clearance_id = ?;";
        return jdbcTemplate.update(sql, sc.getName(), sc.getSecurityClearanceId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int scId)
    {
        // This requires a strategy.
        // It's probably not appropriate to delete agency_agent records that depend on a security clearance.
        // Only allow deletion if a security clearance key isn't referenced.
        final String sql = "delete from security_clearance where security_clearance_id = ?;";
        return jdbcTemplate.update(sql, scId) > 0;
    }
}
