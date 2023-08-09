import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ConnectFour
{
    public static void main(String[] args)
    {
        // Declaring player one and player two pieces
        char playerOnePiece = 'X', playerTwoPiece = '0';

        // This will help determine our winner
        boolean isWinner = false;

        // This will determine if we play again or not
        boolean playAgain = true;
        String playAgainOption = "N";

        // This will determine whose turn it is
        // True will be player one's turn, false will be player two's turn
        boolean playerOneTurn = true;

        // This will determine if the AI plays or not
        boolean twoPlayer = false;
        String twoPlayerOption = "N";

        // This will be the choice of column the player places it in each time
        int colChoice;

        // Creating 2D array for connect four board and setting it up with '_' spaces
        // In a 2D array, the first [] are the rows, and the second [] are the columns
        char[][] connectFourBoard = new char[6][7];

        Scanner input = new Scanner(System.in);

        // This will all keep running as long as we keep playing again
        while (playAgain)
        {
            System.out.print("2 Players? (Y/N): ");
            twoPlayerOption = input.next();
            if (twoPlayerOption.equalsIgnoreCase("Y") || twoPlayerOption.equalsIgnoreCase("N"))
            {
                // Determine to play single or two player
                if (twoPlayerOption.equalsIgnoreCase("Y"))
                    twoPlayer = true;
                else
                    twoPlayer = false;
            }
            else
            {
                // Otherwise, if we get an invalid response, close the program
                break;
            }

            // Printing out our initial board
            for (int x = 0; x < connectFourBoard.length; x ++)
                for (int y = 0; y < connectFourBoard[x].length; y ++)
                    connectFourBoard[x][y] = '_';

            // We want to have a while loop that continues until there is a winner
            while (!isWinner)
            {
                // Let player one or player two go depending on the boolean
                if (playerOneTurn)
                    colChoice = PlayerOneMove(connectFourBoard);
                else
                {
                    // This line will run the player 2 or the AI move depending on how many players
                    colChoice = twoPlayer ? PlayerTwoMove(connectFourBoard) : PlayerTwoAIMove(connectFourBoard);
                    System.out.println();
                }
                System.out.println("Placing it in col " + colChoice);
                // Update and Display the board each time
                System.out.println("-------------------------------");
                UpdateBoard(connectFourBoard, colChoice, (playerOneTurn ? 'X' : '0'));
                PrintOutBoard(connectFourBoard);
                System.out.println("-------------------------------");

                // Change the players turn
                playerOneTurn = !playerOneTurn;

                // Every time, the while loop will end with checking if there is a winner
                isWinner = DetermineWinner(connectFourBoard);
            }

            // Printing out the final board
            System.out.println("Final Board: ");
            PrintOutBoard(connectFourBoard);
            System.out.println("Congrats to the winner! " + (!playerOneTurn ? "Player One!" : "Player Two!"));

            // Checking if the player wants to play again
            System.out.print("Play Again? Y/N: ");
            playAgainOption = input.next();
            if (playAgainOption.equalsIgnoreCase("Y"))
            {
                // Reset variables
                isWinner = false;
                playerOneTurn = true;
                playAgain = true;
            }
            else
                playAgain = false;
        }
    }

    public static boolean DetermineWinner(char[][] connectFourBoard)
    {
        // Checking for Horizontal Wins
        if (HorizontalWin(connectFourBoard))
            return true;

        // Checking for Vertical Wins
        if (VerticalWin(connectFourBoard))
            return true;

        // Checking for Diaognal Wins
        if (DiagonalWin(connectFourBoard))
            return true;

        // Returns false if there is no winner
        return false;
    }

    public static int PlayerOneMove(char[][] connectFourBoard)
    {
        // Use the scanner to get an input of the players choice the play on the board
        Scanner input = new Scanner(System.in);
        System.out.print("Player 1: Select where you would like to drop your piece (1-7): ");
        int colChoice = input.nextInt();
        boolean canPlay =  false;

        // This while loop checks for either out of range, or the board is full
        while (!canPlay)
        {
            // Continue asking for col choice until the player is in range
            if (colChoice > 7 || colChoice < 1)
            {
                System.out.print("Out of range. Please try again: ");
                colChoice = input.nextInt();
            }
            else if (connectFourBoard[0][colChoice-1] != '_')  // Alternatively, if that choice was filled up
            {
                System.out.print("Spot is filled up. Please try again: ");
                colChoice = input.nextInt();
            }
            else   // Otherwise, it is placeable
                canPlay = true;
        }

        // Return the players choice
        return colChoice;
    }

    public static int PlayerTwoMove(char[][] connectFourBoard)
    {
        // Use the scanner to get an input of the players choice the play on the board
        Scanner input = new Scanner(System.in);
        System.out.print("Player 2: Select where you would like to drop your piece (1-7): ");
        int colChoice = input.nextInt();
        boolean canPlay =  false;

        // This while loop checks for either out of range, or the board is full
        while (!canPlay)
        {
            // Continue asking for col choice until the player is in range
            if (colChoice > 7 || colChoice < 1)
            {
                System.out.print("Out of range. Please try again: ");
                colChoice = input.nextInt();
            }
            else if (connectFourBoard[0][colChoice-1] != '_')  // Alternatively, if that choice was filled up
            {
                System.out.print("Spot is filled up. Please try again: ");
                colChoice = input.nextInt();
            }
            else   // Otherwise, it is placeable
                canPlay = true;
        }

        // Return the players choice
        return colChoice;
    }

    public static int PlayerTwoAIMove(char[][] connectFourBoard)
    {
        // Use the scanner to get an input of the players choice the play on the board
        System.out.print("Player 2: Selecting Place to drop...");
        int colChoice = -1;
        boolean madeMove = false;

        System.out.println();

        // First, we want to look for anywhere that has 3 0's
        // If there is a place like that, we check if the 4th slot for the win is a '_'
        // If it is, then we take that spot and the AI wins
        if (!madeMove)
            colChoice = AICheckForPiece(connectFourBoard, 3, '0');
        if (colChoice > 0 && colChoice < 8)
            madeMove = true;

        // Otherwise, we want to look for anywhere that has 3 X's
        // If there is a place like that, we check if the 4th slot for the win is a '_'
        // If it is, then we take that spot and the to prevent player from winning
        if (!madeMove)
            colChoice = AICheckForPiece(connectFourBoard, 3, 'X');
        if (colChoice > 0 && colChoice < 8)
            madeMove = true;

        // Then we check for anywhere that has 2 0's
        // If there is a place like that, we check if the 3rd slot is a '_'
        // If it is, then we take that spot
        if (!madeMove)
            colChoice = AICheckForPiece(connectFourBoard, 2, '0');
        if (colChoice > 0 && colChoice < 8)
            madeMove = true;

        // Otherwise, we check for anywhere that has 2 X's
        // If there is a place like that, we check if the 3rd slot is a '_'
        // If it is, then we take that spot
        if (!madeMove)
            colChoice = AICheckForPiece(connectFourBoard, 2, 'X');
        if (colChoice > 0 && colChoice < 8)
            madeMove = true;

        // If none of these pass, we just play a random piece
        if (!madeMove)
            colChoice = AICheckForPiece(connectFourBoard, 0, '0');

        // Return the players choice
        return colChoice;
    }

    public static int AICheckForPiece(char[][] connectFourBoard, int numInRow, char piece)
    {
        // This boolean will help determine if we made a choice
        int colChoice = -1;
        boolean madeMove = false;

        if (numInRow == 0 || numInRow == 1)
            return ThreadLocalRandom.current().nextInt(1, 7);
        else
        {
            // Check for Horizontal 3/4 Slots Piece
            if (!madeMove)
                colChoice = AIHorizontalFinalCheck(connectFourBoard, piece);
            if (colChoice > 0 && colChoice < 8)
                madeMove = true;

            // Check for Horizontal Piece
            if (!madeMove)
                colChoice = AIHorizontalCheck(connectFourBoard, numInRow, piece);
            if (colChoice > 0 && colChoice < 8)
                madeMove = true;

            // Check for Vertical Piece
            if (!madeMove)
                colChoice = AIVerticalCheck(connectFourBoard, numInRow, piece);
            if (colChoice > 0 && colChoice < 8)
                madeMove = true;

            // Check for Diagonal Piece
            if (!madeMove)
                colChoice = AIDiagonalCheck(connectFourBoard, numInRow, piece);
        }

        // Return false at the end, indicating that a move was not made
        return colChoice;
    }

    public static int AIHorizontalCheck(char[][] connectFourBoard, int numInRow, char pieceToPlay)
    {
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
            {
                char piece = connectFourBoard[x][y];
                // We check y < 7 - numInRow since y represents the columns
                // There are 7 columns, and no point to put one down once past that row
                if (piece == pieceToPlay && y < (7 - numInRow))
                {
                    // We are checking the next x spots on that row depending on the numInRow var
                    for (int z = 0; z <= numInRow; z ++)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return the col to drop it in
                        if (z == numInRow && connectFourBoard[x][y + z] == '_')
                            return (y + z) + 1;   // Add the 1 for the array

                        // Otherwise, if at any point one of them is not our piece, we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[x][y + z] != pieceToPlay)
                            break;
                    }
                }
                // We will also check for an open slot behind
                if (piece == pieceToPlay && y > numInRow)
                {
                    // We are checking the next x spots on that row depending on the numInRow var
                    System.out.println(y + " " + (y - numInRow));
                    for (int z = y; z >= (y - numInRow); z --)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return the col to drop it in
                        if (z == (y - numInRow) && connectFourBoard[x][z] == '_')
                            return z + 1;

                        // Otherwise, if at any point one of t7hem is not our piece, we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[x][z] != pieceToPlay)
                            break;
                    }
                }
            }
        }
        // Return false at the end in the event that there are no wins
        return -1;
    }

    public static int AIHorizontalFinalCheck(char[][] connectFourBoard, char pieceToPlay)
    {
        int horizontalCount = 0;
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
            {
                char piece = connectFourBoard[x][y];
                // We check y < 4 since y represents the columns
                // Once you get past the 4th column, it is impossible to get a horizontal match
                if (piece == 'X' && y < 4)
                {
                    // We are checking the next 4 spots on that row
                    for (int z = 0; z < 4; z ++)
                    {
                        // If at any point one of them is  'X', we increment
                        if (connectFourBoard[x][y + z] == 'X')
                            horizontalCount += 1;
                    }

                    // Now we check, if there is 1 missing spot, then we want to fill it
                    if (horizontalCount == 3)
                    {
                        for (int z = 0; z < 4; z ++)
                        {
                            // If at any point one of them is '_', we fill it
                            if (connectFourBoard[x][y + z] == '_')
                                return (y + z) + 1;
                        }
                    }
                }
            }
        }
        // Return false at the end in the event that there are no wins
        return -1;
    }

    public static int AIVerticalCheck(char[][] connectFourBoard, int numInCol, char pieceToPlay)
    {
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
            {
                char piece = connectFourBoard[x][y];
                // We check x < 6 - numInCol since y represents the columns
                // There are 6 rows, and no point to put one down once past that col
                if (piece == pieceToPlay && x < (6 - numInCol))
                {
                    // We are checking the next x spots on that row depending on the numInRow var
                    for (int z = 0; z <= numInCol; z ++)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return the col to drop it in
                        if (z == numInCol && connectFourBoard[x + z][y] == '_')
                            return y + 1;

                        // Otherwise, f at any point one of them is not our piece, we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[x + z][y] != pieceToPlay)
                            break;
                    }
                }
                // We will also check for an open slot behind
                if (piece == pieceToPlay && x > numInCol)
                {
                    // We are checking the next x spots on that row depending on the numInRow var
                    for (int z = x; z >= (x - numInCol); z --)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return the col to drop it in
                        if (z == numInCol && connectFourBoard[z][y] == '_')
                            return y + 1;

                        // Otherwise, if at any point one of them is not our piece, we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[z][y] != pieceToPlay)
                            break;
                    }
                }
            }
        }
        // Return false at the end in the event that there are no wins
        return -1;
    }

    public static int AIDiagonalCheck(char[][] connectFourBoard, int numInDiag, char pieceToPlay)
    {
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
            {
                char piece = connectFourBoard[x][y];
                // We check y < 4 since y represents the columns
                // Then we check x < 3 since x represents the rows
                // Once you get past the 4th column or the 3rd row, it is impossible to get a diagonal match
                if (piece == pieceToPlay && (y < (7 - numInDiag) && x < (6 - numInDiag)))
                {
                    // We are checking the next 4 spots going ACROSS and DOWN
                    for (int z = 0; z <= numInDiag; z ++)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == numInDiag && connectFourBoard[x + z][y + z] == '_')
                            return (y + z) + 1;

                        // Otherwise, if at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[Math.min(x + z, 5)][Math.min(y + z, 6)] != pieceToPlay)
                            break;
                    }
                }
                if (piece == pieceToPlay && (y > numInDiag && x > numInDiag))
                {
                    // Checking Backwards
                    for (int z = y; z >= (y - numInDiag); z --)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == numInDiag && connectFourBoard[x - z][y - z] == '_')
                            return z + 1;

                        // Otherwise, if at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[Math.max(x - z, 0)][Math.max(y - z, 0)] != pieceToPlay)
                            break;
                    }
                }
                // Now we check x >= 3 since it is going UP (3, 2, 1, 0 in array)
                if (piece == pieceToPlay && (y < (7 - numInDiag) && x > numInDiag))
                {
                    // Now we are checking the next 4 spots going ACROSS and UP
                    for (int z = 0; z <= numInDiag; z ++)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == numInDiag && connectFourBoard[x - z][y + z] == '_')
                            return (y + z) + 1;

                        // Otherwise, if at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[Math.max(x - z, 0)][Math.min(y + z, 6)] != pieceToPlay)
                            break;
                    }
                }
                if (piece == pieceToPlay && (y > numInDiag && x < (6 - numInDiag)))
                {
                    // Checking Backwards
                    for (int z = y; z >= (y - numInDiag); z --)
                    {
                        // We check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == numInDiag && connectFourBoard[Math.min(x + z, 5)][Math.max(y - z, 0)] == '_')
                            return z + 1;

                        // Otherwise, if at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[Math.max(x - z, 0)][Math.max(y - z, 0)] != pieceToPlay)
                            break;
                    }
                }
            }
        }
        // Return false at the end in the event that there are no wins
        return -1;
    }

    public static char[][] UpdateBoard(char[][] connectFourBoard, int colChoice, char symbol)
    {
        // Once this passes, search that column for an open slot
        // We start at 5 because we want to start at the bottom
        for (int x = 5; x >= 0; x --)
        {
            if (connectFourBoard[x][colChoice-1] == '_')
            {
                connectFourBoard[x][colChoice-1] = symbol;
                break;
            }
        }
        return connectFourBoard;
    }

    public static boolean HorizontalWin(char[][] connectFourBoard)
    {
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
            {
                char piece = connectFourBoard[x][y];
                // We check y < 4 since y represents the columns
                // Once you get past the 4th column, it is impossible to get a horizontal match
                if (piece == 'X' && y < 4)
                {
                    // We are checking the next 4 spots on that row
                    for (int z = 0; z < 4; z ++)
                    {
                        // If at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[x][y + z] != 'X')
                            break;

                        // Otherwise we check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == 3)
                            return true;
                    }
                }
                else if (piece == '0' && y < 4)
                {
                    // Here we do the same code, only looking for player 2's win
                    for (int z = 0; z < 4; z ++)
                    {
                        if (connectFourBoard[x][y + z] != '0')
                            break;
                        if (z == 3)
                            return true;
                    }
                }
            }
        }
        // Return false at the end in the event that there are no wins
        return false;
    }

    public static boolean VerticalWin(char[][] connectFourBoard)
    {
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
            {
                char piece = connectFourBoard[x][y];
                // We check x < 4 since x represents the rows
                // Once you get past the 3rd row column, it is impossible to get a horizontal match
                if (piece == 'X' && x < 3)
                {
                    // We are checking the next 4 spots on that row
                    for (int z = 0; z < 4; z ++)
                    {
                        // If at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[x + z][y] != 'X')
                            break;

                        // Otherwise we check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == 3)
                            return true;
                    }
                }
                else if (piece == '0' && x < 3)
                {
                    // Here we do the same code, only looking for player 2's win
                    for (int z = 0; z < 4; z ++)
                    {
                        if (connectFourBoard[x + z][y] != '0')
                            break;
                        if (z == 3)
                            return true;
                    }
                }
            }
        }
        // Return false at the end in the event that there are no wins
        return false;
    }

    public static boolean DiagonalWin(char[][] connectFourBoard)
    {
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
            {
                char piece = connectFourBoard[x][y];
                // We check y < 4 since y represents the columns
                // Then we check x < 3 since x represents the rows
                // Once you get past the 4th column or the 3rd row, it is impossible to get a diagonal match
                if (piece == 'X' && (y < 4 && x < 3))
                {
                    // We are checking the next 4 spots going ACROSS and DOWN
                    for (int z = 0; z < 4; z ++)
                    {
                        // If at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[x + z][y + z] != 'X')
                            break;

                        // Otherwise we check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == 3)
                            return true;
                    }
                }
                else if (piece == '0' && (y < 4 && x < 3))
                {
                    // Here we do the same code, only looking for player 2's win
                    for (int z = 0; z < 4; z ++)
                    {
                        if (connectFourBoard[x + z][y + z] != '0')
                            break;
                        if (z == 3)
                            return true;
                    }
                }

                // Now we check x >= 3 since it is going UP (3, 2, 1, 0 in array)
                if (piece == 'X' && (y < 4 && x >= 3))
                {
                    // Now we are checking the next 4 spots going ACROSS and UP
                    for (int z = 0; z < 4; z ++)
                    {
                        // If at any point one of them is not 'X', we break, stopping the search
                        // Since there is no chance of a connect four
                        if (connectFourBoard[x - z][y + z] != 'X')
                            break;

                        // Otherwise we check if we are at the end and we haven't broken yet, then
                        // We could assume that there are four in a row, thus we return true
                        if (z == 3)
                            return true;
                    }
                }
                else if (piece == '0' && (y < 4 && x >= 3))
                {
                    // Here we do the same code, only looking for player 2's win
                    for (int z = 0; z < 4; z ++)
                    {
                        if (connectFourBoard[x - z][y + z] != '0')
                            break;
                        if (z == 3)
                            return true;
                    }
                }
            }
        }
        // Return false at the end in the event that there are no wins
        return false;
    }

    public static void PrintOutBoard(char[][] connectFourBoard)
    {
        // Go through each row and column
        for (int x = 0; x < connectFourBoard.length; x ++)
        {
            for (int y = 0; y < connectFourBoard[x].length; y ++)
                System.out.print(connectFourBoard[x][y] + " ");
            // This will give us a new line for the next row
            System.out.println();
        }
    }
}