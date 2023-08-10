import java.util.Arrays;

public class Shortcuts
{
    public static void main (String [] args)
    {
        // main => Creates main method

        // sout => Simple print line
        System.out.println();

        // soutp => Print all args passed in method
        System.out.println("args = " + Arrays.toString(args));

        // soutm => Print shortcuts
        System.out.println("Shortcuts.main");

        // soutv => Print all args passed in method
        System.out.println("args = " + args);

        // itar => Iterates elements of array
        for (int i = 0; i < args.length; i++)
        {
            String arg = args[i];
        }

        // ritar => Irrate elements of array in reverse order
        for (int i = args.length - 1; i >= 0; i--)
        {
            String arg = args[i];
        }

        // todo => Adds to do function
        // TODO: 8/10/2023

        // ifn => Inserts if null scene
        if (args == null)
        {

        }

        // inn => Inserts if not null scene
        if (args != null)
        {

        }
    }
}
