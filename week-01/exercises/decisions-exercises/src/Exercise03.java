import java.util.Scanner;

public class Exercise03
{
    public static void main(String[] args)
    {
        // 1. Change the code to accept input from the user.
        // Use a Scanner and parse the input with Integer.parseInt.
        Scanner scan = new Scanner(System.in);
        int value;

        value = Integer.parseInt(scan.next());
        if (value >= 10 && value < 26) {
            System.out.println("Value is in the acceptable range.");
        }

        // Alternatively
        value = scan.nextInt();
        if (value >= 10 && value < 26) {
            System.out.println("Value is in the acceptable range.");
        }
    }
}
