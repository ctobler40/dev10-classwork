import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise06
{
    public static void main(String[] args)
    {
        ArrayList<BoardGame> games = GameRepository.getAll();
        int highestPlayersIndex = 0;

        // 1. Use a loop to find the game in `games` that can be played by the most players.
        System.out.println("Most Players:");
        for (int i = 1; i < games.size(); i++)
        {
            if (games.get(i).getMaxPlayers() > games.get(i - 1).getMaxPlayers())
                highestPlayersIndex = i;
        }
        // 2. Print the game. (Expected: "Ultimate Werewolf...")
        System.out.println(games.get(highestPlayersIndex).getName() + " has " + games.get(highestPlayersIndex).getMaxPlayers() + " players.");
    }
}

