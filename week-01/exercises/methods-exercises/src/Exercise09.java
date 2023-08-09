public class Exercise09
{
    public static void main(String[] args)
    {
        // 2. Call your method in various ways to test it here.
        printBox(5, 10);
        System.out.println("-----------------------------------------------");
        printBox(15, 5);
        System.out.println("-----------------------------------------------");
        printBox(25, 25);
        System.out.println("-----------------------------------------------");
        printBox(50, 10);
        System.out.println("-----------------------------------------------");
    }

    // 1. Create a method.
    // Name: printBox
    // Inputs: int, int
    // Output: void
    // Description: prints a visual box to the console. The first parameter is the number of rows to print.
    // The second parameter is the number of columns.
    // See repetition Exercise15.
    public static void printBox(int rows, int cols)
    {
        char[][] printOut = new char[rows][cols];

        for (int x = 0; x < printOut.length; x ++)
        {
            for (int y = 0; y < printOut[x].length; y ++)
            {
                printOut[x][y] = '#';
                System.out.print(printOut[x][y] + " ");
            }
            System.out.println();
        }
    }

    // Expected Output (5 rows, 5 columns)
    // #####
    // #####
    // #####
    // #####
    // #####

    // (3 rows, 4 columns)
    // ####
    // ####
    // ####
}
