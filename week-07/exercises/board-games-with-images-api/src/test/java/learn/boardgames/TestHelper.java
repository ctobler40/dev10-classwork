package learn.boardgames;

import learn.boardgames.domain.BoardGameResult;
import learn.boardgames.models.Availability;
import learn.boardgames.models.BoardGame;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestHelper {

    public static final int VALID_ID = 5;

    public static BoardGame makeBoardGame(int id) {
        BoardGame game = new BoardGame();
        game.setId(id);
        game.setTitle(String.format("Title #%s", id));
        game.setMinimumPlayers(id);
        game.setMaximumPlayers(id + 2);
        game.setRetailReleaseDate(LocalDate.of(2001, 12, id));
        game.setRating(5.0);
        game.setInCollection(id % 2 == 0);
        game.setAvailability(Availability.UPCOMING);
        game.setMsrp(BigDecimal.valueOf(id + 10.99));
        return game;
    }

    public static BoardGameResult makeResult(String message, BoardGame game) {
        BoardGameResult result = new BoardGameResult();
        if (message != null) {
            result.addMessage(message);
        }
        result.setGame(game);
        return result;
    }
}
