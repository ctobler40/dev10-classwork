package learn.gomoku;
import learn.gomoku.game.Board;
import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class App
{
    // App is the only class you should be modifying
    // Feel free to comment in the other existing classes and create new classes and methods to help
    public static Scanner console = new Scanner(System.in);

    public static void main(String[] args)
    {
        // Play the game
        playGomoku();
    }

    // Main method call from main that plays the game
    public static void playGomoku()
    {
        System.out.println("Welcome to Gomoku!");
        System.out.println("===================");

        // Instantiate our initial variables
        Player p1 = setUpPlayer(1), p2 = setUpPlayer(2);
        Gomoku gomokuGame = new Gomoku(p1, p2);
        Board gameBoard = new Board();
        Result result = new Result("null");

        // Set up and print out our inital game board
        System.out.println();
        gameBoard.initializeGomokuBoard();
        gameBoard.printGomokuBoard();

        // Get the first random player to go
        System.out.println("\n(Randomizing)\n" + gomokuGame.getCurrent().getName() + " will go first!");

        while (!gomokuGame.isOver())
        {
            // If player one is going first, then we set player one's turn whenver isBlacksTurn is true
            // Since black always starts off in Gomoku
            if (gomokuGame.getCurrent() == p1)
                result = playerTurn(gomokuGame, gameBoard, p1, result);
            else
                result = playerTurn(gomokuGame, gameBoard, p2, result);

            // Only print the board if it is a success
            if (result.isSuccess())
                gameBoard.printGomokuBoard();
        }
        // Print the winner once the game is over
        playAgain(result);
    }

    public static void playAgain(Result result)
    {
        System.out.print("\n" + result.getMessage() + "\n\nPlay again? [y/n]: ");
        if (console.nextLine().equalsIgnoreCase("y"))
        {
            System.out.println();
            playGomoku();
        }
    }

    public static Result playerTurn(Gomoku gomokuGame, Board gameBoard, Player p, Result result)
    {
        int row, col;
        if (p instanceof HumanPlayer)
        {
            // First, we just want to get an input from the player
            System.out.println("\n" + p.getName() + "'s tun");
            System.out.print("Enter a row: ");
            row = Integer.parseInt(console.nextLine());
            System.out.print("Enter a column: ");
            col = Integer.parseInt(console.nextLine());
            System.out.println();
        }
        else
        {
            row = ThreadLocalRandom.current().nextInt(1, gameBoard.getWidth());
            col = ThreadLocalRandom.current().nextInt(1, gameBoard.getWidth());
            System.out.println("\n" + p.getName() + " is feeling lucky! They choose " + row + ", " + col + "!\n");
        }

        // Here we check then if the move already has a piece there
        Stone playStone = new Stone(row, col, gomokuGame.isBlacksTurn());

        // We update the visual board only if it is a "_"
        if (playStone.getRow() >= 0 && playStone.getColumn() >= 0 && playStone.getRow() <= 15 && playStone.getColumn() <= 15)
        {
            if (gameBoard.getBoard()[row][col].equals("_"))
                gameBoard.setBoard(playStone);
        }

        // The Gomoku class board then is checked from the result which we get from the place call
        result = gomokuGame.place(playStone);
        if (!result.isSuccess())
            System.out.println("[Err]: " + result.getMessage() + "\n");
        return result;
    }

    // Create a method that sets up the player
    public static Player setUpPlayer(int playerNum)
    {
        // We make a whiel (true) so that it never stops asking until 1 or 2 is picked in which it will return
        while (true)
        {
            System.out.print("\nPlayer " + playerNum + " is:\n1. Human Player\n2. Random Player\nSelect [1-2]: ");
            switch (Integer.parseInt(console.nextLine()))
            {
                case 1:   // Human Player
                    System.out.print("Player " + playerNum + ", enter your name: ");
                    return new HumanPlayer(console.nextLine());
                case 2:   // Random Player
                    RandomPlayer rand = new RandomPlayer();
                    System.out.println("Player 2 will be " + rand.getName());
                    return rand;
                default:
                    break;
            }
        }
    }

    // Simple method to make sure it is a integer
    public static int getInteger(String input)
    {
        int num;
        try
        {
            num = Integer.parseInt(input);
        }
        catch (Exception e)
        {
            num = -1;
        }
        return num;
    }
}
