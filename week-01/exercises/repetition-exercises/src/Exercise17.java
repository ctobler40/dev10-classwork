import java.util.Scanner;

public class Exercise17
{
    public static void main(String[] args)
    {
        // USER-DEFINED BOX
        // 1. Collect the following from a user: rows, columns, box character, border character.
        // 2. Use nested loops to print a user-defined box in the console.
        Scanner scan = new Scanner(System.in);

        System.out.print("Box Character: ");
        String boxChar = scan.next();
        System.out.print("Border Character: ");
        String borderChar = scan.next();
        System.out.print("Rows: ");
        int rows = scan.nextInt();
        System.out.print("Columns: ");
        int cols = scan.nextInt();

        String[][] printOut = new String[rows][cols];

        for (int x = 0; x < printOut.length; x ++)
        {
            for (int y = 0; y < printOut[x].length; y ++)
            {
                if (x == 0 || y == 0 || x == printOut.length-1 || y == printOut[x].length-1)
                    printOut[x][y] = boxChar;
                else
                    printOut[x][y] = borderChar;
                System.out.print(printOut[x][y] + " ");
            }
            System.out.println();
        }

        // (See Exercise16.)
    }
}
