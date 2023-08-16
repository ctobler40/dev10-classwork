import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise08
{
    public static void main(String[] args)
    {
        ArrayList<BoardGame> games = GameRepository.getAll();
        System.out.println(games);

        // 1. Remove the 5th and 10th game from `games`.
        games.remove(4);
        games.remove(9);

        // 2. Print `games`.
        System.out.println(games);
    }
}
