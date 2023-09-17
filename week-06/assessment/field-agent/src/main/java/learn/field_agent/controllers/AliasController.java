package learn.field_agent.controllers;

import learn.field_agent.domain.AliasService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Alias;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agent/alias")
public class AliasController
{
    private final AliasService aliasService;

    public AliasController(AliasService aliasService) {
        this.aliasService = aliasService;
    }

    @GetMapping
    public List<Alias> findAll() {
        return aliasService.findAll();
    }

    @GetMapping("/{aliasId}")
    public ResponseEntity<Alias> findById(@PathVariable int aliasId)
    {
        Alias alias = aliasService.findById(aliasId);
        if (alias == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(alias);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Alias alias)
    {
        Result<Alias> result = aliasService.add(alias);

        if (result.isSuccess())
        {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{aliasId}")
    public ResponseEntity<Object> update(@PathVariable int aliasId, @RequestBody Alias alias)
    {
        if (aliasId != alias.getAliasId())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Alias> result = aliasService.update(alias);
        if (result.isSuccess())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{aliasId}")
    public ResponseEntity<Void> deleteById(@PathVariable int aliasId)
    {
        if (aliasService.deleteById(aliasId))
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
