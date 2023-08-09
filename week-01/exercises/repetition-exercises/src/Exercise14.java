import java.util.Scanner;

public class Exercise14
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int digitCount = 0;

        // 1. Collect a phrase from a user via the console.
        String phrase = scan.nextLine();

        // 2. Count the number of digits in the phrase.
        // Hint: Character.isDigit
        for (int x = 0; x < phrase.length(); x ++)
        {
            if (Character.isDigit(phrase.charAt(x)))
                digitCount += 1;
        }

        // 3. Print the result.
        System.out.println("There were " + digitCount + " digits!");
    }
}
