package learn.field_agent.domain;

import learn.field_agent.data.LocationRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;

import java.util.List;

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

    public boolean deleteById(int agencyId) {
        return repository.deleteById(agencyId);
    }

    private Result<SecurityClearance> validate(SecurityClearance sc)
    {
        Result<SecurityClearance> result = new Result<>();

        if (sc == null) {
            result.addMessage("Security Clearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(sc.getName())) {
            result.addMessage("name is required", ResultType.INVALID);
        }

        return result;
    }
}
