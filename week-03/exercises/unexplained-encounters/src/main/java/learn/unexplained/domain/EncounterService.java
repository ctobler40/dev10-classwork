package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepository;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;

public class EncounterService {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<Encounter> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public EncounterResult add(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);
        if (!result.isSuccess()) {
            return result;
        }

        // check for duplicate
        List<Encounter> encounters = repository.findAll();
        for (Encounter e : encounters) {
            if (Objects.equals(encounter.getWhen(), e.getWhen())
                    && Objects.equals(encounter.getType(), e.getType())
                    && Objects.equals(encounter.getDescription(), e.getDescription())) {
                result.addErrorMessage("duplicate encounter is not allowed");
                return result;
            }
        }

        encounter = repository.add(encounter);
        result.setPayload(encounter);
        return result;
    }

    private EncounterResult validate(Encounter encounter) {

        EncounterResult result = new EncounterResult();
        if (encounter == null) {
            result.addErrorMessage("encounter cannot be null");
            return result;
        }

        if (encounter.getWhen() == null || encounter.getWhen().trim().length() == 0) {
            result.addErrorMessage("when is required");
        }

        if (encounter.getDescription() == null || encounter.getDescription().trim().length() == 0) {
            result.addErrorMessage("description is required");
        }

        if (encounter.getOccurrences() <= 0) {
            result.addErrorMessage("occurrences must be greater than 0");
        }

        return result;
    }

    // Add three new methods:
    // It's up to you to decide the appropriate inputs and outputs. Refer to lessons and existing code for examples and inspiration.
    // Avoid code duplication. Create re-usable methods whenever possible.
    // findByType
    private Encounter findByType(EncounterType type) throws DataAccessException
    {
        List<Encounter> encounters = findAll();

        for (Encounter encounter : encounters)
        {
            if (encounter.getType() == type)
                return encounter;
        }

        return null;
    }

    // update
    // Validation requirements for update:
    // Encounter may not be null.
    // When is required.
    // Description is required.
    // Occurrences must be a positive number.
    // An encounter may not be a duplicate. A duplicate is defined as: same type, when, and
    // description with a different encounterId. (If the encounterIds are the same, it's the same encounter.)
    private EncounterResult update(Encounter encounter) throws DataAccessException
    {
        EncounterResult result = validate(encounter);

        if (result.isSuccess())
        {
            // This may or may not work
            boolean success = repository.update(encounter);
            if (!success)
                result.addErrorMessage("Could not update Encounter: " + encounter.getDescription());
        }

        return result;
    }

    // deleteById
    private boolean deleteById(int id) throws DataAccessException
    {
        List<Encounter> encounters = findAll();

        for (int x = 0; x < encounters.size(); x ++)
        {
            Encounter encounter = encounters.get(x);
            if (encounter.getEncounterId() == id)
            {
                encounters.remove(x);
                return true;
            }
        }

        return false;
    }
}
