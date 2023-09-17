package learn.field_agent.data;

import learn.field_agent.models.Agent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AgentRepository {
    // TODO: Fetch an individual agent with aliases attached?
    List<Agent> findAll();

    Agent findById(int agentId);

    Agent add(Agent agent);

    boolean update(Agent agent);

    @Transactional
    boolean deleteById(int agentId);
}
