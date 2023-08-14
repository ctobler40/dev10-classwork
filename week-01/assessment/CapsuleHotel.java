package assessment;
import javax.swing.text.View;
import java.util.Scanner;

public class CapsuleHotel
{
    // Dev10 Week01 Assessment
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        // All declared variables
        int hotelCapacity = -1, optionChosen = -1;
        String[] capsules;

        // On start up, the application prompts the administrator for the hotel's capacity.
        // The capacity determines how many capsules are available.
        System.out.print("Please enter the Hotel's capacity: ");
        hotelCapacity = input.nextInt();

        // When the program starts up, capsules and guests will be represented by a String[] of the appropriate size.
        capsules = new String[hotelCapacity];
        System.out.println("Welcome to the Capsule Hotel!");
        System.out.println("---------------------------------");

        // Keep running while option != exit
        while (optionChosen != 4)
        {
            // The administrator will have 4 options: Check In, Check Out, View Guests, and Exit
            optionChosen = SelectOption();

            // Now we run each method depending on the option selected
            switch (optionChosen)
            {
                case 1:   // Check In
                    CheckIn(capsules);
                    break;
                case 2:   // Check Out
                    CheckOut(capsules);
                    break;
                case 3:   // View Guests
                    ViewGuests(capsules);
                    break;
                case 4:   // Exit
                    System.out.println("---------------------------------");
                    System.out.println("We thank you for your time here at Capsule Hotel!");
                    System.out.println("---------------------------------");
                    break;
                default:
                    break;
            }
        }
    }

    public static void CheckIn(String[] capsules)
    {
        Scanner input = new Scanner(System.in);

        // The administrator may book a guest in an unoccupied numbered capsule.
        // Unoccupied capsules are represented by a null array value.
        String guestName = "";
        int capsuleNum = -1;
        boolean foundRoom = false;

        // We want to get the Guest Name and Capsule they will be put in
        System.out.println("---------------------------------");
        System.out.print("Guest Name: ");
        guestName = input.nextLine();
        System.out.print("Capsule # [1-" + capsules.length + "]: ");

        // Confirming that it is an open room
        capsuleNum = ConfirmingRoom(capsules, input.next(), 1);

        // Once it passes, we will add them into the String array
        System.out.println("---------------------------------");
        System.out.println("Welcome " + guestName + "! We are adding you to capsule " + capsuleNum + "!");
        capsules[capsuleNum - 1] = guestName;
    }

    public static void CheckOut(String[] capsules)
    {
        Scanner input = new Scanner(System.in);

        // The administrator may check out a guest from an occupied capsule.
        // Occupied capsules are represented by the occupant's name as a String.
        int capsuleNum = -1;
        boolean foundRoom = false;

        // First select the room you want to Check out of
        System.out.println("---------------------------------");
        System.out.print("Capsule # [1-" + capsules.length + "]: ");

        // Confirming that it is an open room
        capsuleNum = ConfirmingRoom(capsules, input.next(), 2);

        // Once it passes, we will add them into the String array
        System.out.println("---------------------------------");
        System.out.println("Goodbye " + capsules[capsuleNum - 1] + "! We appreciate your stay in " + capsuleNum + "!");
        capsules[capsuleNum - 1] = null;
    }

    public static void ViewGuests(String[] capsules)
    {
        Scanner input = new Scanner(System.in);
        int capsuleNum = -1, decrement = 6, increment = 5;

        // The administrator may view guests and their capsule numbers in groups of 11.
        if (capsules.length <= 11)
        {
            // When asking to view capsules, if # capsules <= 11, then we don't bother asking for the capsule #!
            for (int x = 0; x < capsules.length; x ++)
                System.out.println((x + 1) + ": " + (capsules[x] == null ? "[Unoccupied]" : capsules[x]));
        }
        else
        {
            // First select the room you want to view
            System.out.println("---------------------------------");
            System.out.print("Capsule # [1-" + capsules.length + "]: ");

            // Confirming that it is an available room
            // With the optionSelected argument as 3, it will only check for the # being in range
            capsuleNum = ConfirmingRoom(capsules, input.next(), 3);

            // Printing out guests
            System.out.println("---------------------------------");
            System.out.println("Capsule: Guest");

            // We will also need to check here if we are out of range on either side
            // If we are, then we print the other way
            if (capsuleNum < 6)
            {
                decrement -= (6 - capsuleNum);
                increment += (6 - capsuleNum);
            }
            else if (capsuleNum > capsules.length - 5)
            {
                increment -= (capsuleNum - (capsules.length - 5));
                decrement += (capsuleNum - (capsules.length - 5));
            }
            for (int x = capsuleNum - decrement; x < capsuleNum + increment; x ++)
                System.out.println((x + 1) + ": " + (capsules[x] == null ? "[Unoccupied]" : capsules[x]));
        }
    }

    public static int ConfirmingRoom(String[] capsules, String capsuleString, int optionSelected)
    {
        Scanner input = new Scanner(System.in);
        boolean foundRoom = false;
        int capsuleNum = -1;

        while (!foundRoom)
        {
            // We run this to make sure anything like 'asdasd' is not allowed
            capsuleNum = ConfirmForInt(capsuleString);
            if (capsuleNum < 1 || capsuleNum > capsules.length)
            {
                // We first check to see if it is in range
                System.out.println("---------------------------------");
                System.out.println("We don't have that room here!");
                System.out.println("Please enter a new capsule #!");
                System.out.println("---------------------------------");
                System.out.print("Capsule # [1-" + capsules.length + "]: ");

                capsuleString = input.nextLine();
            }
            else if ((capsules[capsuleNum - 1] != null && optionSelected == 1) || (capsules[capsuleNum - 1] == null && optionSelected == 2))
            {
                // Now we check if it is occupied or unoccupied depending on the boolean parameter passed in
                System.out.println("---------------------------------");
                if (optionSelected == 1)
                    System.out.println("Sorry. It looks like " + capsules[capsuleNum - 1] + " is occupying that room =(");
                else
                    System.out.println("Sorry. It looks like capsule " + capsuleNum + " has no occupant in that room =(");
                System.out.println("Please enter a new capsule #!");
                System.out.println("---------------------------------");
                System.out.print("Capsule # [1-" + capsules.length + "]: ");

                capsuleString = input.nextLine();
            }
            else
            {
                // Otherwise, we have an open room
                System.out.println("---------------------------------");
                System.out.println("That room is acceptable!");
                foundRoom = true;
            }
        }
        return capsuleNum;
    }

    public static int ConfirmForInt(String capsuleChoice)
    {
        int capsuleNum = -1;
        try
        {
            capsuleNum = Integer.parseInt(capsuleChoice);
        }
        catch (Exception e)
        {
            capsuleNum = -1;
        }
        return capsuleNum;
    }

    public static int SelectOption()
    {
        Scanner input = new Scanner(System.in);
        int option = -1;
        String optionChoice = "";

        // Keep going until we get a valid option
        while (option == -1)
        {
            System.out.println("Please Select your option:");
            System.out.println("---------------------------------");
            System.out.println("1: Check In");
            System.out.println("2: Check Out");
            System.out.println("3: View Guests");
            System.out.println("4: Exit");
            System.out.println("---------------------------------");
            System.out.print("I select ");
            optionChoice = input.nextLine();
            option = ConfirmForInt(optionChoice);
            if (!(option < 5 && option > 0))
            {
                // Still no valid response
                System.out.println("Not Applicable! Need a # between 1 and 4!");
                System.out.println("---------------------------------");
                option = -1;
            }
        }
        return option;
    }
}
