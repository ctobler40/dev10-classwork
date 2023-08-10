import java.util.Random;

public class Exercise12
{
    public static void main(String[] args)
    {
        int[] values = MakeRandomArray();

        // 1. Count the number of positive and non-positive elements in `values`.
        int numPos = 0, numNeg = 0;
        for (int value : values)
        {
            if (value % 2 == 0)
                numPos += 1;
            else
                numNeg += 1;
        }

        // 2. Create two new int[]s, one for positive elements and one for non-positive.
        // (We count first to determine the capacity to allocate.)
        int[] posArray = new int[numPos];
        int[] negArray = new int[numNeg];

        // 3. Loop through `values` a second time. If an element is positive, add it to the positive array.
        // If it is non-positive, add it to the non-positive array.
        int posArrayPos = 0;
        int negArrayPos = 0;
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] % 2 == 0)
            {
                posArray[posArrayPos] = values[i];
                posArrayPos += 1;
            }
            else
            {
                negArray[negArrayPos] = values[i];
                negArrayPos += 1;
            }
        }

        // 4. Confirm that your secondary arrays are properly populated either by debugging or printing their elements.
        for (int x = 0; x < posArray.length; x ++)
            System.out.println(posArray[x]);
        for (int x = 0; x < negArray.length; x ++)
            System.out.println(negArray[x]);
    }

    public static int[] MakeRandomArray()
    {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        for (int i = 0; i < result.length; i++)
            result[i] = random.nextInt(1000) - 500;

        return result;
    }
}
