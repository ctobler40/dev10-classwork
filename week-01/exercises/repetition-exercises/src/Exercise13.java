import java.util.Scanner;
public class Exercise13
{

    public static void main(String[] args)
    {
        // DOUBLE IT
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String phrase = console.nextLine();
        String doublePhrase = "";

        // 1. Write a loop that "doubles" each character in a user-entered word.
        // You'll need a new string variable to store the result.
        for (int x = 0; x < phrase.length(); x ++)
        {
            doublePhrase += (phrase.substring(x, x + 1) + "" + phrase.substring(x, x + 1));
        }

        // 2. Print the result.
        System.out.println(doublePhrase);

        // Examples
        // ===============
        // "dog" -> "ddoogg"
        // "what?" -> "wwhhaatt??"
        // "" -> "" (empty string has nothing to double)
        // " " -> "  " (but whitespace should be doubled)
        // "open & shut" -> "ooppeenn  &&  sshhuutt"
        // "Eep" -> "EEeepp"
    }
}
