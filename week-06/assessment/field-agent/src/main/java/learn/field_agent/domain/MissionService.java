package learn.field_agent.domain;

import learn.field_agent.data.MissionRepository;
import learn.field_agent.models.Mission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Mission findById(int missionId) {
        return missionRepository.findById(missionId);
    }

    public Result<Mission> add(Mission mission) {
        Result<Mission> result = validate(mission);
        if (!result.isSuccess()) {
            return result;
        }

        if (mission.getMissionId() != 0) {
            result.addMessage("missionId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        mission = missionRepository.add(mission);
        result.setPayload(mission);
        return result;
    }

    public Result<Mission> update(Mission mission) {
        Result<Mission> result = validate(mission);
        if (!result.isSuccess()) {
            return result;
        }

        if (mission.getMissionId() <= 0) {
            result.addMessage("missionId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!missionRepository.update(mission)) {
            String msg = String.format("missionId: %s, not found", mission.getMissionId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int missionId) {
        return missionRepository.deleteById(missionId);
    }

    private Result<Mission> validate(Mission mission) {
        Result<Mission> result = new Result<>();
        if (mission == null) {
            result.addMessage("mission cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(mission.getCodeName())) {
            result.addMessage("codeName is required", ResultType.INVALID);
        }

        if (mission.getStartDate() == null) {
            result.addMessage("starDate is required", ResultType.INVALID);
        }

        if (mission.getEndDate_Projected() == null) {
            result.addMessage("projected endDate is required", ResultType.INVALID);
        }

        if (mission.getEndDate_Actual() == null) {
            result.addMessage("actual endDate is required", ResultType.INVALID);
        }

        if (mission.getOperationalCost() == null) {
            result.addMessage("operationCost is required", ResultType.INVALID);
        }

        if (mission.getAgency() == null) {
            result.addMessage("agency is required", ResultType.INVALID);
        }

        return result;
    }
}