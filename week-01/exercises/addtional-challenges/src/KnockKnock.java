import java.util.Scanner;

public class KnockKnock
{
    public static void main (String [] args)
    {
        // My Task
        // Knock Knock Joke

        String response = "", prompt = "Tree";
        Scanner input = new Scanner(System.in);

        System.out.println("Knock Knock");
        response = input.nextLine();
        while (!response.equalsIgnoreCase("Who's There?"))
        {
            System.out.println("That's not how you respond!");
            System.out.println("Knock Knock");
            response = input.nextLine();
        }

        System.out.println(prompt);
        response = input.nextLine();
        while (!response.equalsIgnoreCase(prompt + " who?"))
        {
            System.out.println("That's not how you respond!");
            System.out.println(prompt);
            response = input.nextLine();
        }

        System.out.println(DetermineReponse(prompt));
    }

    public static String DetermineReponse(String prompt)
    {
        if (prompt == "Jalapeno")
            return "Orange you glad I didn't say Jalapeno?!";
        return "What was the joke again?";
    }
}
