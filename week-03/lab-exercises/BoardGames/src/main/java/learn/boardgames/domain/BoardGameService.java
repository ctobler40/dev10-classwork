package learn.boardgames.domain;

import learn.boardgames.data.BoardGameRepository;
import learn.boardgames.data.DataAccessException;
import learn.boardgames.models.BoardGame;

import java.util.List;

public class BoardGameService {
    private final BoardGameRepository repository;

    public BoardGameService(BoardGameRepository repository) {
        this.repository = repository;
    }

    // Read
    public List<BoardGame> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public List<BoardGame> findByTitle(String title) throws DataAccessException {
        return repository.findByTitle(title);
    }

    public BoardGame findById(int gameId) throws DataAccessException {
        return repository.findById(gameId);
    }

    // Create
    public BoardGameResult add(BoardGame game) throws DataAccessException {

        BoardGameResult result = validate(game);

        if (result.isSuccess()) {
            BoardGame bg = repository.add(game);
            result.setGame(bg);
        }

        return result;
    }

    // Update

    public BoardGameResult update(BoardGame game) throws DataAccessException {

        BoardGameResult result = validate(game);
        if (!result.isSuccess()) {
            return result;
        }

        if (game.getId() <= 0) {
            result.addMessage("game id is required");
            return result;
        }

        boolean success = repository.update(game);
        if (!success) {
            result.addMessage("could not update game id " + game.getId());
        }

        return result;
    }

    public BoardGameResult deleteById(int gameId) throws DataAccessException {
        BoardGameResult result = new BoardGameResult();
        boolean success = repository.deleteById(gameId);
        if (!success) {
            result.addMessage("could not delete game id " + gameId);
        }
        return result;
    }

    private BoardGameResult validate(BoardGame game) throws DataAccessException {
        BoardGameResult result = new BoardGameResult();
        if (game == null) {
            result.addMessage("game cannot be null");
            return result;
        }

        if (game.getTitle() == null || game.getTitle().isBlank()) {
            result.addMessage("game title cannot be blank");
        }

        if (game.getMinimumPlayers() <= 0) {
            result.addMessage("min players must be greater than 0");
        }

        if (game.getMaximumPlayers() < game.getMinimumPlayers()) {
            result.addMessage("max players must be greater than or equal to min players");
        }

        if (game.getRating() < 0.0 || game.getRating() > 10.0) {
            result.addMessage("rating must be between 0 and 10");
        }

        if (game.getRetailReleaseDate() == null) {
            result.addMessage("release date is required");
        } else if (!game.getRetailReleaseDate().matches("\\d{4}-[01]?\\d-((3[01])|([012]?\\d))")) {
            result.addMessage("release date has wrong format. required: yyyy-MM-dd");
        }

        if (game.getAvailability() == null) {
            result.addMessage("availability is required");
        }

        if (!result.isSuccess()) {
            return result;
        }

        // duplicate game
        List<BoardGame> matchingTitles = repository.findByTitle(game.getTitle());
        for (BoardGame bg : matchingTitles) {
            if (bg.getRetailReleaseDate().equals(game.getRetailReleaseDate())
                    && bg.getId() != game.getId()) {
                result.addMessage("duplicate game");
            }
        }

        return result;
    }

}
