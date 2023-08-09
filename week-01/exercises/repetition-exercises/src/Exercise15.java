import java.util.Scanner;

public class Exercise15
{
    public static void main(String[] args)
    {
        // BOX
        // 1. Use nested loops to print a box in the console.
        // One loop should represent rows and the other should represent columns.
        // 2. Change the row and column limit to change the shape of the box.
        Scanner scan = new Scanner(System.in);

        System.out.println("""
                Rows
                Columns
                """);
        char[][] printOut = new char[scan.nextInt()][scan.nextInt()];

        for (int x = 0; x < printOut.length; x ++)
        {
            for (int y = 0; y < printOut[x].length; y ++)
            {
                printOut[x][y] = '#';
                System.out.print(printOut[x][y] + " ");
            }
            System.out.println();
        }

        // Expected Output (5X5)
        // #####
        // #####
        // #####
        // #####
        // #####

        // (3X4)
        // ####
        // ####
        // ####
    }
}
