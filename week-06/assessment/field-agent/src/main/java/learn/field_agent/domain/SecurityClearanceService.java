package learn.field_agent.domain;

import learn.field_agent.data.LocationRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService
{
    private final SecurityClearanceRepository repository;

    public SecurityClearanceService(SecurityClearanceRepository repository) {
        this.repository = repository;
    }

    public List<SecurityClearance> findAll() {
        return repository.findAll();
    }

    public SecurityClearance findById(int id) {
        return repository.findById(id);
    }

    public Result<SecurityClearance> add(SecurityClearance sc)
    {
        Result<SecurityClearance> result = validate(sc);
        if (!result.isSuccess())
        {
            return result;
        }

        result = validateName(sc);
        if (!result.isSuccess())
        {
            return result;
        }

        if (sc.getSecurityClearanceId() != 0)
        {
            result.addMessage("securityClearanceId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        sc = repository.add(sc);
        result.setPayload(sc);
        return result;
    }

    public Result<SecurityClearance> update(SecurityClearance sc)
    {
        Result<SecurityClearance> result = validate(sc);
        if (!result.isSuccess()) {
            return result;
        }

        result = validateName(sc);
        if (!result.isSuccess())
        {
            return result;
        }

        if (sc.getSecurityClearanceId() <= 0)
        {
            result.addMessage("securityClearanceId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(sc))
        {
            String msg = String.format("securityClearanceId: %s, not found", sc.getSecurityClearanceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<SecurityClearance> deleteById(int scId)
    {
        // This requires a strategy.
        // It's probably not appropriate to delete agency_agent records that depend on a security clearance.
        // Only allow deletion if a security clearance key isn't referenced.
        Result<SecurityClearance> result = validate(repository.findById(scId));
        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.deleteById(scId))
        {
            String msg = String.format("securityClearanceId: %s, not found", scId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<SecurityClearance> validate(SecurityClearance sc)
    {
        Result<SecurityClearance> result = new Result<>();

        if (sc == null)
        {
            result.addMessage("Security Clearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(sc.getName()))
        {
            result.addMessage("name is required", ResultType.INVALID);
            return result;
        }

        if (repository.agentCount(sc.getSecurityClearanceId()) > 0)
        {
            result.addMessage("cannot delete Security Clearance with active agents", ResultType.INVALID);
            return result;
        }

        return result;
    }

    // Clearance Checks:
    // 1. Security clearance name is required.
    // 2. Name cannot be duplicated.
    private Result<SecurityClearance> validateName(SecurityClearance sc)
    {
        Result<SecurityClearance> result = new Result<>();
        List<SecurityClearance> scs = repository.findAll();

        for (int i = 0; i < scs.size(); i++)
        {
            SecurityClearance thisSc = scs.get(i);
            if (thisSc.getName().equalsIgnoreCase(sc.getName()))
            {
                result.addMessage("Security Clearance cannot have the same name", ResultType.INVALID);
            }
        }

        return result;
    }
}
