package learn.field_agent.domain;

import learn.field_agent.data.AgentAliasRepository;
import learn.field_agent.data.AgentRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.AgentAlias;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgentService {

    private final AgentRepository repository;

    private final AgentAliasRepository aliasRepository;

    public AgentService(AgentRepository repository, AgentAliasRepository aliasRepository) {
        this.repository = repository;
        this.aliasRepository = aliasRepository;
    }
    public List<Agent> findAll() {
        return repository.findAll();
    }

    public Agent findById(int agentId) {
        return repository.findById(agentId);
    }

    public Result<Agent> add(Agent agent) {
        Result<Agent> result = validate(agent);
        if (!result.isSuccess()) {
            return result;
        }

        if (agent.getAgentId() != 0) {
            result.addMessage("agentId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        agent = repository.add(agent);
        result.setPayload(agent);
        return result;
    }

    public Result<Agent> update(Agent agent) {
        Result<Agent> result = validate(agent);
        if (!result.isSuccess()) {
            return result;
        }

        if (agent.getAgentId() <= 0) {
            result.addMessage("agentId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(agent)) {
            String msg = String.format("agentId: %s, not found", agent.getAgentId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int agentId) {
        return repository.deleteById(agentId);
    }

    public Result<AgentAlias> addAlias(AgentAlias alias) {
        Result<AgentAlias> result = validateAlias(alias);

        if (!result.isSuccess()) {
            return result;
        }

        result = validatePersona(alias);
        if (!result.isSuccess()) {
            return result;
        }

        if (alias.getAliasId() != 0) {
            result.addMessage("aliasId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        alias = aliasRepository.add(alias);
        result.setPayload(alias);
        return result;
    }

    public Result<AgentAlias> updateAlias(AgentAlias alias) {
        Result<AgentAlias> result = validateAlias(alias);
        if (!result.isSuccess()) {
            return result;
        }

        result = validatePersona(alias);
        if (!result.isSuccess()) {
            return result;
        }

        if (alias.getAliasId() <= 0) {
            result.addMessage("aliasId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        return result;
    }

    public boolean deleteAliasById(int aliasId) {
        return repository.deleteById(aliasId);
    }

    private Result<AgentAlias> validateAlias(AgentAlias alias) {
        Result<AgentAlias> result = new Result<>();

        if (alias == null) {
            result.addMessage("alias cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(alias.getName())) {
            result.addMessage("name is required", ResultType.INVALID);
            return result;
        }

        if (repository.findById(alias.getAgentId()) == null){
            result.addMessage("cannot find corresponding agent", ResultType.INVALID);
            return result;
        }

        // Persona is not required unless a name is duplicated. The persona differentiates between duplicate names.
        if (Validations.isNullOrBlank(alias.getPersona())) {
            if (aliasRepository.findByName(alias.getName()).size() > 1) {
                result.addMessage("similar names. Persona is required", ResultType.INVALID);
                return result;
            }
        }

        return result;
    }

    // Clearance Checks:
    // 1. Security clearance name is required.
    // 2. Name cannot be duplicated.
    private Result<AgentAlias> validatePersona(AgentAlias alias)
    {
        Result<AgentAlias> result = new Result<>();
        List<Agent> agents = repository.findAll();

        for (int i = 0; i < agents.size(); i++)
        {
            List<AgentAlias> thisAlias = agents.get(i).getAliases();
            if (thisAlias.contains(alias.getName()))
            {
                result.addMessage("Alias cannot have the same name", ResultType.INVALID);
            }
        }

        return result;
    }

    private Result<Agent> validate(Agent agent) {
        Result<Agent> result = new Result<>();
        if (agent == null) {
            result.addMessage("agent cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(agent.getFirstName())) {
            result.addMessage("firstName is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(agent.getLastName())) {
            result.addMessage("lastName is required", ResultType.INVALID);
        }

        if (agent.getDob() != null && agent.getDob().isAfter(LocalDate.now().minusYears(12))) {
            result.addMessage("agents younger than 12 are not allowed", ResultType.INVALID);
        }

        if (agent.getHeightInInches() < 36 || agent.getHeightInInches() > 96) {
            result.addMessage("height must be between 36 and 96 inches", ResultType.INVALID);
        }

        return result;
    }
}
