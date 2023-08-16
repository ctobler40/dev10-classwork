import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise13
{
    private ArrayList<BoardGame> games = GameRepository.getAll();
    private Scanner console = new Scanner(System.in);

    public static void main(String[] args)
    {
        Exercise13 instance = new Exercise13();
        instance.run();
    }

    private void printHeader(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    private void run()
    {
        printHeader("Welcome to the Board Game Manager.");

        String input;
        do
        {
            printHeader("Menu");
            System.out.println("1. Add a board game.");
            System.out.println("2. Display all board games.");
            System.out.println("3. Remove a board game.");
            System.out.println("4. Exit");
            System.out.print("Select [1-4]: ");
            input = console.nextLine();

            switch (input)
            {
                case "1":
                    // 1. Create a method that gathers user input via `console` to instantiate a BoardGame
                    // and then adds it to the `games` list.
                    // 2. Replace the line of code below with a call to the method.
                    AddGame();
                    break;
                case "2":
                    // 3. Create a method to display all board games in the `games` list.
                    // 4. Replace the line of code below with a call to the method.
                    DisplayGames();
                    break;
                case "3":
                    // 5. Stretch Goal: Create a method that allows the user to remove a board game from the
                    // `games` list with an index.
                    // 6. Replace the line of code below with a call to the method.
                    System.out.print("Select Index to Remove: ");
                    RemoveGame(Integer.parseInt(console.nextLine()));
                    break;
                case "4":
                    printHeader("Goodbye.");
                    break;
                default:
                    System.out.printf("%nI don't understand '%s'.%n", input);
                    break;
            }
        }
        while (!input.equals("4"));
    }

    private void AddGame()
    {
        String name = "", category = "";
        int min = 0, max = 0;
        System.out.println("Enter Name: ");
        name = console.nextLine();
        System.out.println("Enter Min Players: ");
        min = Integer.parseInt(console.nextLine());
        System.out.println("Enter Max Players: ");
        max = Integer.parseInt(console.nextLine());
        System.out.println("Enter Category: ");
        category = console.nextLine();
        games.add(new BoardGame(name, min, max, category));
    }

    private void DisplayGames()
    {
        System.out.println("Games:");
        for (int i = 0; i < games.size(); i++)
        {
            BoardGame boardGame =  games.get(i);
            System.out.println(boardGame.getName());
        }
    }

    private void RemoveGame(int index)
    {
        if (index > 0 && index < games.size())
            games.remove(index - 1);
        else
            System.out.println("Out of Range!");
    }
}
