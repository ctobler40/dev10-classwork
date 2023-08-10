import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Exercise10
{
    public static void main(String[] args)
    {
        String[] bugs = MakeBugArray();

        // The bugs array elements are either the value "beetle" or "mosquito".
        // 1. Count the number of beetles and mosquitoes.
        int numBeetles = 0, numMisquitoes = 0;
        for (String bug : bugs)
        {
            if (bug.equals("beetle"))
                numBeetles += 1;
            else
                numMisquitoes += 1;
        }

        // 2. Print the result.
        System.out.println(numBeetles + " Beetles and " + numMisquitoes + " Misquitoes");
        // Results will vary because of randomness.
    }

    public static String[] MakeBugArray()
    {
        String[] bugs = new String[200];
        Arrays.fill(bugs, "beetle");
        Random random = new Random();
        for (int i = 0; i < random.nextInt(150); i++)
            bugs[random.nextInt(bugs.length)] = "mosquito";

        return bugs;
    }
}
