package assessment;
import javax.swing.text.View;
import java.util.Scanner;

public class CapsuleHotel
{
    public static void main(String[] args)
    {
        // All declared variables
        int hotelCapacity = -1, optionChosen = -1;
        String[] capsules;
        Scanner input = new Scanner(System.in);

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
                    CheckIn();
                    break;
                case 2:   // Check Out
                    CheckOut();
                    break;
                case 3:   // View Guests
                    ViewGuests();
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

        // The administrator may book a guest in an unoccupied numbered capsule.
        // Unoccupied capsules are represented by a null array value.

        // The administrator may check out a guest from an occupied capsule.
        // Occupied capsules are represented by the occupant's name as a String.

        // The administrator may view guests and their capsule numbers in groups of 11.
        // When asking to view capsules, if # capsules < 11, then we don't bother asking for the capsule #!
    }

    public static void CheckIn()
    {

    }

    public static void CheckOut()
    {

    }

    public static void ViewGuests()
    {

    }

    public static int SelectOption()
    {
        int option = -1;
        Scanner input = new Scanner(System.in);

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
            option = input.nextInt();
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

    // At least one method beyond main is required. A few more methods may make your life easier.
}
