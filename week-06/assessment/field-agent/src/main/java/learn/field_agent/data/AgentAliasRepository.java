package learn.field_agent.data;

import learn.field_agent.models.AgentAlias;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AgentAliasRepository
{
    List<AgentAlias> findAll();

    AgentAlias findById(int id);

    List<AgentAlias> findByName(String name);

    AgentAlias add(AgentAlias alias);

    boolean update(AgentAlias alias);

    @Transactional
    boolean deleteById(int aliasId);
}
