import java.util.Scanner;

public class Exercise19
{
    public static void main(String[] args)
    {
        // INTERLEAVE
        Scanner console = new Scanner(System.in);

        System.out.print("First string: ");
        String first = console.nextLine();

        System.out.print("Second string: ");
        String second = console.nextLine();

        String finalString = "";

        // 1. Write a loop to interleave two strings to form a new string.
        // To interleave, during each loop take one character from the first string and add it to the result
        // and take one character from the second string and add it to the result.
        // If there are no more characters available, don't add characters.
        if (first != "" && second != "")
        {
            if (first.length() < second.length())
            {
                for (int x = 0; x < first.length(); x ++)
                {
                    finalString += (first.substring(x, x+1) + second.substring(x, x+1));
                }
                finalString += second.substring(first.length(), first.length());
            }
            else
            {
                for (int x = 0; x < second.length(); x ++)
                {
                    finalString += (first.substring(x, x+1) + second.substring(x, x+1));
                }
                finalString += first.substring(second.length(), first.length());
            }
        }
        System.out.println(finalString);

        // 2. Print the result.

        // Examples
        // "abc", "123" -> "a1b2c3"
        // "cat", "dog" -> "cdaotg"
        // "wonder", "o" -> "woonder"
        // "B", "igstar" -> "Bigstar"
        // "", "huh?" -> "huh?"
        // "wha?", "" -> "wha?"
    }
}
