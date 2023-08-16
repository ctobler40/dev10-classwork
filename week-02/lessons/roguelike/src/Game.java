import java.util.Random;
import java.util.Scanner;

public class Game
{
    // constants
    private static int WIDTH = 10;
    private final static String WALL_CHARACTER = "M";
    private final static String EMPTY_CHARACTER = " ";

    private GamePiece[][] gameBoard;
    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private Treasure[] treasures = new Treasure[4];
    private Trap[] traps = new Trap[4];
    private boolean isOver;
    private boolean foundTreasure = false;
    private int numTreasuresFound = 0;

    public void run()
    {
        // ALlow user to change size of world
        System.out.print("How large do you want the world (1-50)?: ");
        WIDTH = Integer.parseInt(console.nextLine());
        gameBoard = new GamePiece[WIDTH][WIDTH];

        setUp();
        while (!isOver)
        {
            printWorld(gameBoard);
            move(gameBoard);
        }
        printWorld(gameBoard);
    }

    private void setUp()
    {
        System.out.print("What is the name of your hero?: ");
        String name = console.nextLine();

        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);

        hero = new Hero(name, x, y);

        // 1. Allow the user to choose their hero's symbol.
        System.out.print("What symbol do you want to use?: ");
        hero.setSymbol(console.nextLine().charAt(0));

        for (int i = 0; i < treasures.length; i++)
        {
            do
            {
                x = rand.nextInt(WIDTH);
                y = rand.nextInt(WIDTH);
            }
            while (x == hero.getX() && y == hero.getY());

            treasures[i] = new Treasure(x, y, '$');
        }

        for (int i = 0; i < traps.length; i++)
        {
            for (int j = 0; j < treasures.length; j ++)
            {
                do
                {
                    x = rand.nextInt(WIDTH);
                    y = rand.nextInt(WIDTH);
                }
                while ((x == hero.getX() && y == hero.getY()) || (x == treasures[j].getX() && y == treasures[j].getY()));

                traps[i] = new Trap(x, y, '^');
            }
        }
    }

    private void printWorld(GamePiece[][] gameBoard)
    {
        // top wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for (int row = 0; row < WIDTH; row++)
        {
            // left wall border
            System.out.print(WALL_CHARACTER);
            for (int col = 0; col < WIDTH; col++)
            {
                for (int i = 0; i < treasures.length; i ++)
                {
                    if (row == treasures[i].getY() && col == treasures[i].getX() && !treasures[i].getTreasureStatus())
                    {
                        gameBoard[row][col] = new Treasure(row, col);
                        System.out.print(treasures[i].getSymbol());
                        foundTreasure = true;
                    }
                }
                for (int i = 0; i < traps.length; i ++)
                {
                    if (row == traps[i].getY() && col == traps[i].getX() && !traps[i].getTrapStatus())
                    {
                        gameBoard[row][col] = new Trap(row, col);
                        System.out.print(traps[i].getSymbol());
                        foundTreasure = true;
                    }
                }
                if (!foundTreasure)
                {
                    if (row == hero.getY() && col == hero.getX())
                    {
                        gameBoard[row][col] = new Hero("name", row, col);
                        System.out.print(hero.getSymbol());
                    }
                    else
                    {
                        gameBoard[row][col] = new Blank(row, col);
                        System.out.print(EMPTY_CHARACTER);
                    }
                }
                foundTreasure = false;
            }

            // right wall border
            System.out.println(WALL_CHARACTER);
        }

        // bottom wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));
    }

    private void move(GamePiece[][] gameBoard)
    {
        System.out.print(hero.getName() + ", move [WASD]: ");
        String move = console.nextLine().trim().toUpperCase();

        if (move.length() != 1)
        {
            return;
        }

        switch (move.charAt(0))
        {
            case 'W':
                hero.moveUp();
                break;
            case 'A':
                hero.moveLeft();
                break;
            case 'S':
                hero.moveDown();
                break;
            case 'D':
                hero.moveRight();
                break;
        }

        if (hero.getX() < 0 || hero.getX() >= WIDTH || hero.getY() < 0 || hero.getY() >= WIDTH)
        {
            System.out.println(hero.getName() + " touched lava! You lose.");
            isOver = true;
        }
        for (int i = 0; i < treasures.length; i ++)
        {
            if (hero.getX() == treasures[i].getX() && hero.getY() == treasures[i].getY() && !treasures[i].getTreasureStatus())
            {
                System.out.println(hero.getName() + " found a treasure!");
                treasures[i].setTreasureStatus(true);
                if (++numTreasuresFound == treasures.length)
                {
                    System.out.println(hero.getName() + " found the treasure! You win.");
                    isOver = true;
                }
            }
        }
        for (int i = 0; i < traps.length; i ++)
        {
            if (hero.getX() == traps[i].getX() && hero.getY() == traps[i].getY() && !traps[i].getTrapStatus())
            {
                System.out.println(hero.getName() + " found the trap! You lose.");
                isOver = true;
            }
        }
    }
}
