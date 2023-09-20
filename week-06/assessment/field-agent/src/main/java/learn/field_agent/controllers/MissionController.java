package learn.field_agent.controllers;

import learn.field_agent.domain.MissionService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Mission;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mission")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService)
    {
        this.missionService = missionService;
    }

    @GetMapping
    public List<Mission> findAll() {
        return missionService.findAll();
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<Mission> findById(@PathVariable int missionId) {
        Mission mission = missionService.findById(missionId);
        if (mission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(mission);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Mission mission) {
        Result<Mission> result = missionService.add(mission);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{missionId}")
    public ResponseEntity<Object> update(@PathVariable int missionId, @RequestBody Mission mission) {
        if (missionId != mission.getMissionId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Mission> result = missionService.update(mission);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{missionId}")
    public ResponseEntity<Void> deleteById(@PathVariable int missionId) {
        if (missionService.deleteById(missionId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
