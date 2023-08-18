package learn.gomoku.game;

public class Board
{
    private final int PRINTWIDTH = 16;
    private String[][] printBoard = new String[PRINTWIDTH][PRINTWIDTH];

    public int getWidth()
    {
        return PRINTWIDTH;
    }

    public String[][] getBoard()
    {
        return printBoard;
    }

    private boolean isValid(Stone stone)
    {
        return stone != null
                && stone.getRow() >= 0 && stone.getRow() < PRINTWIDTH - 1
                && stone.getColumn() >= 0 && stone.getColumn() < PRINTWIDTH - 1;
    }

    public Stone setBoard(Stone stone)
    {
        if (isValid(stone))
            printBoard[stone.getRow()][stone.getColumn()] = (stone.isBlack() ? "B" : "W");
        return stone;
    }

    public void printGomokuBoard()
    {
        for (int i = 0; i < printBoard.length; i++)
        {
            for (int j = 0; j < printBoard[i].length; j++)
                System.out.print(" " + printBoard[i][j] + ((i != 0 && j != 0) ? "  " : " "));
            System.out.println();
        }
    }

    public void initializeGomokuBoard()
    {
        // First space is black
        printBoard[0][0] = " ";

        // First row and column is numbered
        for (int i = 1; i <= 9; i ++)
        {
            printBoard[i][0] = "0" + Integer.toString(i);
            printBoard[0][i] = "0" + Integer.toString(i);
        }
        for (int i = 10; i <= Gomoku.WIDTH; i ++)
        {
            printBoard[i][0] = Integer.toString(i);
            printBoard[0][i] = Integer.toString(i);
        }

        // Rest of the board is set up
        for (int i = 1; i <= Gomoku.WIDTH; i ++)
            for (int j = 1; j <= Gomoku.WIDTH; j ++)
                printBoard[i][j] = "_";
    }
}
