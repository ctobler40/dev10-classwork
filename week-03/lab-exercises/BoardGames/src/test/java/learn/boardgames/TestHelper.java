package learn.boardgames;

import learn.boardgames.domain.BoardGameResult;
import learn.boardgames.models.Availability;
import learn.boardgames.models.BoardGame;

public class TestHelper {

    public static final int VALID_ID = 5;

    public static BoardGame makeBoardGame(int id) {
        return new BoardGame(
                id,
                String.format("Title #%s", id),
                id,
                id + 2,
                String.format("2000-01-%02d", id),
                5.0,
                id % 2 == 0,
                Availability.UPCOMING
        );
    }

    public static BoardGameResult makeResult(String message, BoardGame game) {
        BoardGameResult result = new BoardGameResult();
        if(message != null) {
            result.addMessage(message);
        }
        result.setGame(game);
        return result;
    }
}
