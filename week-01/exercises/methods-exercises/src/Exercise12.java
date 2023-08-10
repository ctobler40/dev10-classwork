import java.util.Scanner;

public class Exercise12
{
    public static void main(String[] args)
    {
        // 3. Uncomment the code below and confirm it works.
        PrintNounPhrase();
        PrintNounPhrase();
        PrintNounPhrase();
    }

    // 1. Create a method.
    // Name: readRequiredString
    // Inputs: String
    // Output: String
    // Description: prompts a user to enter a required string and returns their validated input.
    // The parameter is the message displayed to the user.
    //
    // See the readRequiredString implementation in the methods lesson.
    // You can definitely improve it. Make sure you don't allow blank input. Checking the length() is not enough.
    public static String ReadRequiredString(String string)
    {
        Scanner console = new Scanner(System.in);
        while (string.length() == 0)
        {
            string = console.nextLine();
        }

        return string;
    }

    // 2. Create a method.
    // Name: printNounPhrase
    // Inputs: none
    // Output: none
    // Description: prints an adjective + noun phrase to the console based on user input.
    // Internally, prompts a user for an adjective and a noun with readRequiredString.
    // Concatenates adjective and noun and prints it to the console.
    public static void PrintNounPhrase()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Noun: " + ReadRequiredString(console.nextLine()));
        System.out.println("Adjective: " + ReadRequiredString(console.nextLine()));
    }
}
