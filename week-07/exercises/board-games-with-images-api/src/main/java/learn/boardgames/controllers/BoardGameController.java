package learn.boardgames.controllers;

import learn.boardgames.domain.BoardGameResult;
import learn.boardgames.domain.BoardGameService;
import learn.boardgames.models.BoardGame;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class BoardGameController {

    private final BoardGameService service;

    public BoardGameController(BoardGameService service) {
        this.service = service;
    }

    // R - GET
    @GetMapping
    public List<BoardGame> find(@RequestParam(required = false) String title) {
        if (title == null || title.isBlank()) {
            return service.findAll();
        }
        return service.findByTitle(title);
    }

    // C - POST
    @PostMapping
    public ResponseEntity<BoardGameResult> add(@RequestBody BoardGame game) {
        BoardGameResult result = service.add(game);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    // U - PUT
    @PutMapping("/{id}")
    public ResponseEntity<BoardGameResult> update(@PathVariable int id, @RequestBody BoardGame game) {
        if (game != null && id != game.getId()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        BoardGameResult result = service.update(game);
        if (result.isSuccess()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); // SUCCESS!
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    // D - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardGameResult> deleteById(@PathVariable int id) {
        BoardGameResult result = service.deleteById(id);
        if (result.isSuccess()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        if (result.getMessages().get(0).contains("could not delete")) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
