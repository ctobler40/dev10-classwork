import java.util.Random;

public class Exercise11
{
    public static void main(String[] args)
    {
        int[] values = MakeRandomArray();

        // 1. Count the number of positive elements in `values`.
        int numPos = 0;
        for (int i = 0; i < values.length; i++)
            if (values[i] % 2 == 0)
                numPos += 1;

        // 2. Create a new int[] to hold the positive elements.
        // (We must count first to know the capacity to allocate.)
        int[] posArray = new int[numPos];

        // 3. Loop through `values` a second time. Add positive elements to the new array.
        int posArrayPos = 0;
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] % 2 == 0)
            {
                posArray[posArrayPos] = values[i];
                posArrayPos += 1;
            }
        }

        // 4. Confirm the positive array is properly populated either by debugging or printing its elements.
        for (int x = 0; x < posArray.length; x ++)
            System.out.println(posArray[x]);
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
