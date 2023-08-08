import java.util.Scanner;

// 1. Add a class, `Exercise09`, to this project.
public class Exercise09
{
    // 2. Add a `main` method.
    public static void main(String [] args)
    {
        // 3. Declare a `Scanner` variable.
        Scanner scan = new Scanner(System.in);

        // 4. Collect three pieces of data from the user: a minimum value, a maximum value, and an actual value.
        System.out.print("Enter Min: ");
        int min = scan.nextInt();

        System.out.print("Enter Max: ");
        int max = scan.nextInt();

        System.out.print("Enter Actual: ");
        int actual = scan.nextInt();

        // 5. Add `if/else` statements to determine if the actual value is between the min and max.
        // 6. Print messages for both true and false cases.
        if (actual > min && actual < max)
            System.out.print("True!");
        else
            System.out.print("False!");
    }
}
