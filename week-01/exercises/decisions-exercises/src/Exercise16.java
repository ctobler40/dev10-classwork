import java.util.Scanner;

public class Exercise16
{
    public static void main(String[] args)
    {
        // 1. Display the following menu and collect an integer choice from the user.
        // (See Exercise14 for a menu example.)
        //
        // Menu
        // 1. Print the name of an animal.
        // 2. Print the name of a state.
        // 3. Print the name of a beetle.
        // 4. Print the name of a mineral.
        // Select [1-4]:
        //
        Scanner scan = new Scanner(System.in);

        System.out.println("""
                Menu
                1. Print the name of an animal.
                2. Print the name of a state.
                3. Print the name of a beetle.
                4. Print the name of a mineral.
                Select [1-4]:
                """);

        int option = scan.nextInt();
        String word = switch (option)
        {
            case 1 -> "animal names function";
            case 2 -> "state names function";
            case 3 -> "beetle names function";
            case 4 -> "mineral names function";
            default -> "Unknown Menu Option";
        };
        // 2. Use a switch to cover cases 1-4 as well as a default.
        // For 1-4, print an animal, state, beetle, or mineral respectively.
        // For the default case, print "Unknown Menu Option".
        System.out.println(word);
    }
}
