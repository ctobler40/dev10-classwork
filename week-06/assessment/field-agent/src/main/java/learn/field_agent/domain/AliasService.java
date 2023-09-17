package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AliasService {

    private final AliasRepository repository;

    public AliasService(AliasRepository repository) {
        this.repository = repository;
    }

    public List<Alias> findAll() {
        return repository.findAll();
    }

    public Alias findById(int aliasId) {
        return repository.findById(aliasId);
    }

    public Result<Alias> add(Alias alias) {
        Result<Alias> result = validate(alias);
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

        alias = repository.add(alias);
        result.setPayload(alias);
        return result;
    }

    public Result<Alias> update(Alias alias) {
        Result<Alias> result = validate(alias);
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

        if (!repository.update(alias)) {
            String msg = String.format("aliasId: %s, not found", alias.getAliasId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int aliasId) {
        return repository.deleteById(aliasId);
    }

    private Result<Alias> validate(Alias alias) {
        Result<Alias> result = new Result<>();
        if (alias == null) {
            result.addMessage("alias cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(alias.getName())) {
            result.addMessage("name is required", ResultType.INVALID);
            return result;
        }

        // Persona is not required unless a name is duplicated. The persona differentiates between duplicate names.
        if (Validations.isNullOrBlank(alias.getPersona())) {
            if (repository.findByName(alias.getName()).size() > 1) {
                result.addMessage("similar names. Persona is required", ResultType.INVALID);
                return result;
            }
        }

        return result;
    }

    // Clearance Checks:
    // 1. Security clearance name is required.
    // 2. Name cannot be duplicated.
    private Result<Alias> validatePersona(Alias alias)
    {
        Result<Alias> result = new Result<>();
        List<Alias> aliases = repository.findAll();

        for (int i = 0; i < aliases.size(); i++)
        {
            Alias thisAlias = aliases.get(i);
            if (thisAlias.getName().equalsIgnoreCase(alias.getName()))
            {
                result.addMessage("Alias cannot have the same name", ResultType.INVALID);
            }
        }

        return result;
    }
}
