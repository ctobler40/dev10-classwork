package learn.boardgames.domain;

import learn.boardgames.data.BoardGameRepository;
import learn.boardgames.models.BoardGame;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

@Service
public class BoardGameService {
    private final BoardGameRepository repository;

    public BoardGameService(BoardGameRepository repository) {
        this.repository = repository;
    }

    // Read
    public List<BoardGame> findAll() {
        return repository.findAll();
    }

    public List<BoardGame> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public BoardGame findById(int gameId) {
        return repository.findById(gameId);
    }

    // Create
    public BoardGameResult add(BoardGame game) {

        BoardGameResult result = validate(game);

        if (result.isSuccess()) {
            BoardGame bg = repository.add(game);
            result.setGame(bg);
        }

        return result;
    }

    // Update

    public BoardGameResult update(BoardGame game) {

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

    public BoardGameResult deleteById(int gameId) {
        BoardGameResult result = new BoardGameResult();
        boolean success = repository.deleteById(gameId);
        if (!success) {
            result.addMessage("could not delete game id " + gameId);
        }
        return result;
    }

    public HashMap<String, BigDecimal> generateStats() {
        List<BoardGame> all = findAll();
        HashMap<String, BigDecimal> report = new HashMap<>();

        // Create a big decimal total
        BigDecimal total = BigDecimal.ZERO;

        // Loop over all the board games
        for (BoardGame bg : all) {
            // Increment the total value
            total = total.add(bg.getMsrp());
        }


        // Put "Total Value" into the report
        report.put("Total Value", total);

        // Calculate the average by dividing it by the number of board games
        BigDecimal average = total.divide(BigDecimal.valueOf(all.size()), 2, RoundingMode.HALF_EVEN);
        // Put the average in the report
        report.put("Average MSRP", average);

        return report;

    }

    private BoardGameResult validate(BoardGame game) {
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
        }

        if (game.getAvailability() == null) {
            result.addMessage("availability is required");
        }

        if (game.getMsrp() == null) {
            result.addMessage("MSRP is required");
        } else if (game.getMsrp().compareTo(BigDecimal.ZERO) < 0) {
            result.addMessage("MSRP cannot be negative");
        }

        if(game.getImageUrl() != null) {
            game.setImageUrl(game.getImageUrl().trim());
            if(game.getImageUrl().length() > 0) {
                try {
                    URL url = new URL(game.getImageUrl());
                } catch (MalformedURLException e) {
                    result.addMessage("not a valid URL");
                }
            }
        }

        if (!result.isSuccess()) {
            return result;
        }

        // duplicate game
        List<BoardGame> matchingTitles = repository.findByTitle(game.getTitle());
        for (BoardGame bg : matchingTitles) {
            if (bg.getTitle().equalsIgnoreCase(game.getTitle())
                    && bg.getRetailReleaseDate().equals(game.getRetailReleaseDate())
                    && bg.getId() != game.getId()) {
                result.addMessage("duplicate game");
            }
        }

        return result;
    }

}
