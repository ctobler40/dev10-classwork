import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise07
{
    public static void main(String[] args)
    {
        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame> and call it `economicGames`.
        ArrayList<BoardGame> economicGames = new ArrayList<BoardGame>();

        // 2. Loop over `games`. Add each game with the "Economic" category to `economicGames`.
        System.out.println("Economic Games:");
        for (int i = 0; i < games.size(); i++)
        {
            BoardGame game = games.get(i);
            if (game.getCategory().equals("Economic"))
                economicGames.add(game);
        }

        // 3. Print `economicGames`.
        System.out.println(economicGames);
    }
}
