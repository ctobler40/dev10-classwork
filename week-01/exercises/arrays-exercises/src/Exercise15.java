import java.util.Arrays;
import java.util.Random;

public class Exercise15
{
    public static void main(String[] args)
    {
        int[] one = MakeRandomArray();
        int[] two = MakeRandomArray();

        // 1. Create a new int[] with room enough for all elements in `one` and `two`.
        int[] fullArray = new int[one.length + two.length];

        // 2. Copy elements from `one` into the beginning of the array.
        for (int i = 0; i < one.length; i++)
            fullArray[i] = one[i];

        // 3. Copy elements from `two` at the end of the array.
        for (int i = 0; i < two.length; i++)
            fullArray[one.length + i] = two[i];

        // 4. Print the results to confirm that it worked.
        System.out.println(Arrays.toString(one));
        System.out.println(Arrays.toString(two));
        System.out.println(Arrays.toString(fullArray));
    }

    public static int[] MakeRandomArray()
    {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(1000) - 500;
        }
        return result;
    }
}
