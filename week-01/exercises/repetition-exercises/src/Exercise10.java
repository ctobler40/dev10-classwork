import java.util.Scanner;

public class Exercise10
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        System.out.print("Start: ");
        int start = Integer.parseInt(console.nextLine());

        System.out.print("End: ");
        int end = Integer.parseInt(console.nextLine());

        // 1. Write a loop to sum all numbers between start and end.
        int num = 0;
        for (int x = start; x <= end; x ++)
            num += x;

        // 2. Print the result.
        System.out.print(num);
    }
}
