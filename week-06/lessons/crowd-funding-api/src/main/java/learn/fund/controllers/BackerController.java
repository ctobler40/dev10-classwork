package learn.fund.controllers;

import learn.fund.domain.BackerService;
import learn.fund.domain.Result;
import learn.fund.models.Backer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static learn.fund.controllers.ResultToResponseEntity.toResponseEntity;

// The @RestController registers the BackerController in the Spring IoC container.
// It also lets Spring MVC know this is a rest controller and configures handler methods with appropriate mappings.

// The @ControllerAdvice and @ExceptionHandler annotations allow us to handle exceptions consistently across all controllers.
@RestController
// @RequestMapping("/api/backer") sets one base URL for all BackerController requests.
@RequestMapping("/api/backer")
public class BackerController
{
    // BackerController is a @RestController that manages our Backer model.
    // It supports full CRUD.
    // The GetMapping is for reads, @PostMapping is for create, @PutMapping is for update, and @DeleteMapping is for delete.
    private final BackerService service;

    // Since our BackerService is registered with a @Service annotation,
    // it's available to be injected and is provided via the BackerController constructor.
    public BackerController(BackerService service) {
        this.service = service;
    }

    // @RequestParam(required = true) String criteria requires a query string key of criteria,
    // which maps to a URL like /api/backer?criteria=some-criteria.
    @GetMapping
    public List<Backer> findByNameContains(@RequestParam(required = true) String criteria) {
        // One solution is to catch the topmost exception, Exception, in controller handlers.
        // Edit BackerController to catch Exception in its findByNameContains handler.
        return service.findNameContains(criteria);
    }

    // @RequestBody Backer backer tells Spring MVC to transform this JSON backer in a request body into a Backer argument.
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Backer backer) {
        Result<Backer> result = service.add(backer);
        return toResponseEntity(result, HttpStatus.CREATED);
    }

    // @PathVariable int backerId tells Spring MVC wto scan the URL for a backerId:
    // /api/backer/25, here "25", parse it into an integer, and pass it as an argument.
    @PutMapping("/{backerId}")
    public ResponseEntity<?> update(@PathVariable int backerId, @RequestBody Backer backer) {
        if (backerId != backer.getBackerId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<?> result = service.update(backer);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{backerId}")
    public ResponseEntity<?> deleteById(@PathVariable int backerId) {
        Result<?> result = service.deleteById(backerId);
        return toResponseEntity(result, HttpStatus.NO_CONTENT);
    }
}
