import java.util.Scanner;

public class Exercise13
{
    public static void main(String[] args)
    {
        // NEEDLE & HAYSTACK
        Scanner console = new Scanner(System.in);

        System.out.print("Needle: ");
        String needle = console.nextLine();

        System.out.print("Haystack: ");
        String haystack = console.nextLine();

        // 1. Given two string variables: needle and haystack, determine if haystack contains needle.
        // Examples
        // needle  haystack contains?
        //     an      bean      yes
        //    een      bean       no
        //    ury   Mercury      yes
        //    ury     curry       no
        //    mer   Mercury       no (case sensitive)
        // 2. As a stretch goal, display the location (index) of needle in haystack.
        if (haystack.contains(needle))
        {
            System.out.println("Is is there!");
            //System.out.println(haystack.compareTo(needle));
            //if (haystack.compareTo(needle) >= 0)
            //    System.out.println("Found between index 0 and " + (needle.length() - 1) + "!");
            //else
            //    System.out.println("Found between index " + -(haystack.compareTo(needle)) + " and " + (needle.length() - 1) + "!");
        }
        else
        {
            System.out.println("Is is NOT there!");
        }
    }
}
