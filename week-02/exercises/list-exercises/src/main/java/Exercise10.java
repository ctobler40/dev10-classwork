import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise10
{
    public static void main(String[] args)
    {
        ArrayList<BoardGame> games = GameRepository.getAll();
        System.out.println(games);

        // 1. Loop over `games` and remove any game that can be played by one person.
        for (int i = 0; i < games.size(); i++)
        {
            BoardGame game = games.get(i);
            if (game.getMinPlayers() == 1)
                games.remove(game);
        }

        // 2. Print `games` and confirm single-player games are removed.
        System.out.println(games);
    }
}
