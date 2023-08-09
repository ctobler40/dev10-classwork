import java.util.Scanner;

public class Exercise15
{
    public static void main(String [] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int num = scan.nextInt();
        while (num < 0)
        {
            System.out.print("Enter a positive integer: ");
            num = scan.nextInt();
        }
        for (int x = 0; x <= num; x ++)
        {
            DivisibleByFizzBuzz(x);
        }
    }

    public static void DivisibleByFizzBuzz(int num)
    {
        if (num % 3 == 0 && num % 5 == 0)
            System.out.println("Fizz Buzz");
        else
        {
            if (num % 3 == 0)
                System.out.println("Fizz");
            else if (num % 5 == 0)
                System.out.println("Buzz");
            else
                System.out.println(num);
        }
    }

    /* FIZZ BUZZ

    Historically, the Fizz Buzz (https://en.wikipedia.org/wiki/Fizz_buzz) problem was used in programming interviews.
    Not sure if it still is. Just in case, we'll get it out of the way in Milestone 1.

    Write a program to:
    - Prompt a user for a positive integer and store the result. (Could reuse a readInt method.)
    - Loop from the number 1 to the user's integer.
    - If the number is divisible by 3, print Fizz.
    - If the number is divisible by 5, print Buzz.
    - If the number is divisible by both 3 and 5, print Fizz Buzz.
    - If the number is not divisible by either 3 or 5, print the number.

    Example Output:
    1
    2
    Fizz
    4
    Buzz
    Fizz
    7
    8
    Fizz
    Buzz
    11
    Fizz
    13
    14
    Fizz Buzz
    16
    17
    Fizz
     */
}
