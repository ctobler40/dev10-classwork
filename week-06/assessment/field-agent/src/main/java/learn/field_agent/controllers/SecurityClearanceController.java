package learn.field_agent.controllers;

import learn.field_agent.domain.AgencyService;
import learn.field_agent.domain.Result;
import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.Agency;
import learn.field_agent.models.SecurityClearance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security-clearance")
public class SecurityClearanceController
{
    private final SecurityClearanceService securityClearanceService;

    public SecurityClearanceController(SecurityClearanceService securityClearanceService) {
        this.securityClearanceService = securityClearanceService;
    }

    @GetMapping
    public List<SecurityClearance> findAll() {
        return securityClearanceService.findAll();
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<SecurityClearance> findById(@PathVariable int agencyId)
    {
        SecurityClearance sc = securityClearanceService.findById(agencyId);
        if (sc == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(sc);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody SecurityClearance sc)
    {
        Result<SecurityClearance> result = securityClearanceService.add(sc);

        if (result.isSuccess())
        {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{agencyId}")
    public ResponseEntity<Object> update(@PathVariable int securityClearanceId, @RequestBody SecurityClearance sc)
    {
        if (securityClearanceId != sc.getSecurityClearanceId())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<SecurityClearance> result = securityClearanceService.update(sc);
        if (result.isSuccess())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{agencyId}")
    public ResponseEntity<Void> deleteById(@PathVariable int securityClearanceId)
    {
        if (securityClearanceService.deleteById(securityClearanceId))
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
