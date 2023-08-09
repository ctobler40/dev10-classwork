import java.util.Scanner;

public class Exercise15
{
    public static void main(String[] args)
    {
        // SWITCH OPPOSITES
        // Given a word, print its opposite using a switch statement.
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String word = console.nextLine();
        String opposite = switch (word)
        {
            case "big" -> "small";
            case "round" -> "straight";
            case "tall" -> "short";
            case "bear" -> "packer";
            default -> "none";
        };

        // 1. Re-implement Exercise08 using a switch statement.
        System.out.println("Opposite: " + opposite);
    }
}
