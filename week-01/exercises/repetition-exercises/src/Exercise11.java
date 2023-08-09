import java.util.Scanner;

public class Exercise11
{
    public static void main(String[] args)
    {
        /*
        1. Add a new class, `Exercise11`, to this project.
        2. Add a `main` method.
        3. Collect three integers from a user: `start`, `end`, and `increment`.
        4. Write a loop to sum values from `start` to `end` counting by the `increment`.
        5. Print the result.
         */
        Scanner console = new Scanner(System.in);

        System.out.print("Start: ");
        int start = console.nextInt();

        System.out.print("End: ");
        int end = console.nextInt();

        System.out.print("Increment: ");
        int increment = console.nextInt();

        int total = 0;
        for (int x = start; x <= end; x += increment)
            total += x;
        System.out.println(total);
    }
}
