package learn.boardgames.domain;

import learn.boardgames.models.BoardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardGameResult {
    private BoardGame game;
    private ArrayList<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public BoardGame getGame() {
        return game;
    }

    public void setGame(BoardGame game) {
        this.game = game;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardGameResult that = (BoardGameResult) o;

        if (!Objects.equals(game, that.game)) return false;
        return Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        return result;
    }
}
