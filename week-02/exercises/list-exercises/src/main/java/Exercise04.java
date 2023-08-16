import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise04
{
    public static void main(String[] args)
    {
        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame>.
        ArrayList<BoardGame> board = new ArrayList<BoardGame>();

        // 2. Add three BoardGames to the new list.
        board.add(new BoardGame("Monopoly", 1, 4, "'Fun'"));
        board.add(new BoardGame("Checkers", 1, 2, "Kids"));
        board.add(new BoardGame("Chess", 1, 2, "Strategy"));

        // 3. Print the new list.
        System.out.println(board);

        // 4. Add items in the new list to `games` with the `addAll` method.
        games.addAll(board);

        // 5. Print `games`.
        System.out.println(games);
    }
}
