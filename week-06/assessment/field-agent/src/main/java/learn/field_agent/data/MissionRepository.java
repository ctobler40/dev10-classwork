package learn.field_agent.data;

import learn.field_agent.models.Agency;
import learn.field_agent.models.Mission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MissionRepository {
    List<Mission> findAll();

    @Transactional
    Mission findById(int missionId);

    Mission add(Mission mission);

    boolean update(Mission mission);

    @Transactional
    boolean deleteById(int missionId);
}
