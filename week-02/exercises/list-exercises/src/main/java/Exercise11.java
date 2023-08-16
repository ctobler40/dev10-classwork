import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise11
{
    public static void main(String[] args)
    {
        ArrayList<BoardGame> games = GameRepository.getAll();
        System.out.println(games);

        // 1. Swap the 2nd game with the 4th and the 3rd with the 7th.
        Swap(games, 1, 3);
        Swap(games, 2, 6);

        // 2. Print `games` and confirm their order.
        System.out.println(games);
    }

    public static void Swap(ArrayList<BoardGame> games, int index1, int index2)
    {
        BoardGame temp = games.get(index1);
        games.set(index1, games.get(index2));
        games.set(index2, temp);
    }
}
