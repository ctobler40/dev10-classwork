import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise05
{
    public static void main(String[] args)
    {
        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Loop over each BoardGame in `games` and print games with the "Adventure" category.
        System.out.println("Adventure Games:");
        for (int i = 0; i < games.size(); i++)
        {
            BoardGame game = games.get(i);
            if (game.getCategory().equals("Adventure"))
                System.out.println(game.getName());
        }
    }
}
