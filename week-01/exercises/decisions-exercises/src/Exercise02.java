import java.util.Scanner;

public class Exercise02
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a whole number: ");
        String input = console.nextLine();

        // Don't worry about bad input. e.g. if the user enters "pppffghht".
        int value = 0;
        try
        {
            value = Integer.parseInt(input);
        }
        catch (Exception e)
        {
            value = -1;
            System.out.println("Not an integer!");
        }

        // 1. Add an if statement that determines if value is even.
        // 2. If it is, print a message.
        if (value % 2 == 0)
            System.out.println("Even Value: " + value);
    }
}
