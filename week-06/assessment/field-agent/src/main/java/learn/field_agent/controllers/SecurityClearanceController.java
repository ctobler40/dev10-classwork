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
@RequestMapping("/api/securityClearance")
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

    @GetMapping("/{securityClearanceId}")
    public ResponseEntity<SecurityClearance> findById(@PathVariable int securityClearanceId)
    {
        SecurityClearance sc = securityClearanceService.findById(securityClearanceId);
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

    @PutMapping("/{securityClearanceId}")
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

    // This requires a strategy.
    // It's probably not appropriate to delete agency_agent records that depend on a security clearance.
    // Only allow deletion if a security clearance key isn't referenced.
    @DeleteMapping("/{securityClearanceId}")
    public ResponseEntity<Object> deleteById(@PathVariable int securityClearanceId)
    {
        // How could you update this to return result information instead of status codes?
        // What status code would you return if the user tried to delete a SC in use?
        Result<SecurityClearance> result = securityClearanceService.deleteById(securityClearanceId);
        if (result.isSuccess())
        {
            // Return no content if it runs properly
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Otherwise, we return the error we are getting
        return ErrorResponse.build(result);
    }
}
