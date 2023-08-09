import java.util.Scanner;

public class Exercise10
{
    public static void main(String[] args)
    {
        // USPS
        // Below is an abbreviated version of the US Postal Service retail parcel rates:
        /*
        Lbs | Zones 1&2 | Zone 3
        ===============
        1      $7.50      $7.85
        2       8.25       8.70
        3       8.70       9.70
        4       9.20      10.55
        5      10.20      11.30
        */

        // 1. Collect the parcel lbs and zone (1, 2, or 3) from the user.
        // 2. Add `if`/`else if`/`else` logic to cover all rates.
        // Use whatever strategy you think is best. You can create compound conditions or nest if/else statements.
        // If a lbs/zone combo does not exist, print a warning message for the user.

        Scanner console = new Scanner(System.in);

        System.out.print("Enter lbs: ");
        int lbs = console.nextInt();
        System.out.print("Enter zone: ");
        int zone = console.nextInt();
        if (zone == 1 || zone == 2)
        {
            switch (lbs)
            {
                case 1 -> System.out.print("$7.50");
                case 2 -> System.out.print("$8.25");
                case 3 -> System.out.print("$8.70");
                case 4 -> System.out.print("$9.20");
                case 5 -> System.out.print("$10.20");
                default -> { }
            }
        }
        else if (zone == 3)
        {
            switch (lbs)
            {
                case 1 -> System.out.print("$7.85");
                case 2 -> System.out.print("$8.70");
                case 3 -> System.out.print("$9.70");
                case 4 -> System.out.print("$10.55");
                case 5 -> System.out.print("$11.30");
                default -> { }
            }
        }
        else
        {
            System.out.print("Warning: Outside of the Zone!");
        }
    }
}
