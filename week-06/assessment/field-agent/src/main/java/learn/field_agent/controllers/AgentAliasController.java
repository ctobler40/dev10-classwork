package learn.field_agent.controllers;

import learn.field_agent.domain.AgentService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.AgentAlias;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agent/alias")
public class AgentAliasController
{
    private final AgentService aliasService;

    public AgentAliasController(AgentService aliasService) {
        this.aliasService = aliasService;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody AgentAlias alias)
    {
        Result<AgentAlias> result = aliasService.addAlias(alias);

        if (result.isSuccess())
        {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{aliasId}")
    public ResponseEntity<Object> update(@PathVariable int aliasId, @RequestBody AgentAlias alias)
    {
        if (aliasId != alias.getAliasId())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<AgentAlias> result = aliasService.updateAlias(alias);
        if (result.isSuccess())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{aliasId}")
    public ResponseEntity<Void> deleteById(@PathVariable int aliasId)
    {
        if (aliasService.deleteAliasById(aliasId))
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
