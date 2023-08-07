public class Exercise15
{
    public static void main(String[] args)
    {
        int value = 114;

        // 1. Declare a boolean variable inRange.
        boolean inRange = false;

        // 2. When value is between 10 and 20 OR value is between 110 and 120, inRange is true. Otherwise, it's false.
        // 3. Build an expression using value to set inRange.
        if ((value > 10 && value < 20) || (value > 110 && value < 120))
            inRange = true;

        // 4. Print the result.
        System.out.println(inRange);
        System.out.println();

        // 5. Change value to test different cases:
        // - less than 10
        // - between 10 and 20
        // - between 21 and 109
        // - between 110 and 120
        // - greater than 120
        int numRuns = 0;
        boolean[] testCases = { (value < 10), (value > 10 && value < 20), (value > 21 && value < 109), (value > 110 && value < 120), (value > 120) };
        while (numRuns < 5)
        {
            if (testCases[numRuns])
                inRange = true;
            else
                inRange = false;
            System.out.println(inRange);
            numRuns ++;
        }
    }
}
