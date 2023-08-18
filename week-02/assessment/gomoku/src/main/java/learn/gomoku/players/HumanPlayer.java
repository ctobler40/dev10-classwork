package learn.gomoku.players;

import learn.gomoku.game.Stone;

import java.util.List;

public class HumanPlayer implements Player
{
    private final String name;

    public HumanPlayer(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Stone generateMove(List<Stone> previousMoves)
    {
        // This will always return null because it is up to the player to decide where to place it
        return null;
    }
}
