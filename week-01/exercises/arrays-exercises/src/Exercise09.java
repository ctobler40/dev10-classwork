public class Exercise09
{
    public static void main(String[] args)
    {
        String[] haystack = MakeHaystack();

        // A needle is randomly placed in a haystack array with a capacity of 100.
        // 1. Loop through the haystack and find the needle.
        // 2. Print the index where you found it.
        for (int i = 0; i < haystack.length; i++)
        {
            String needle = haystack[i];
            try
            {
                if (needle.equals("needle"))
                {
                    System.out.println("Found needle in slot " + (i + 1) + "!");
                    break;
                }
            }
            catch (Exception e)
            {
                // Do Nothing
            }
        }

        // Hint: this is an exercise about the default value of strings.
    }

    public static String[] MakeHaystack()
    {
        String[] haystack = new String[100];
        int index = (int) (Math.random() * haystack.length);
        haystack[index] = "needle";
        return haystack;
    }
}
