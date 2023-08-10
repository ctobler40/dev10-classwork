import java.util.Arrays;
import java.util.Random;

public class Exercise16
{
    public static void main(String[] args)
    {
        // MERGE
        int[] one = MakeRandomAscendingArray();
        int[] two = MakeRandomAscendingArray();

        // makeRandomAscendingArray creates a random array with a capacity between 50 and 150.
        // Its elements are guaranteed to be sorted ascending.
        // 1. Create a new int[] with capacity for all elements from `one` and `two`.
        int[] fullArray = new int[one.length + two.length];


        for (int i = 0; i < two.length; i++)
            fullArray[one.length + i] = two[i];

        // 2. "Merge" elements from `one` and `two` into the new array so that its values are sorted.
        int increment = 0;
        if (one.length > two.length)
        {
            for (int i = 0; i < two.length; i++)
            {
                fullArray[i + increment] = one[i];
                fullArray[i + increment + 1] = two[i];
                increment += 1;
            }
            for (int  j = two.length; j < one.length; j++)
                fullArray[j] = one[j];
        }
        else
        {
            for (int i = 0; i < one.length; i++)
            {
                fullArray[i + increment] = one[i];
                fullArray[i + increment + 1] = two[i];
                increment += 1;
            }
            for (int  j = one.length; j < two.length; j++)
                fullArray[j] = two[j];
        }

        System.out.println(Arrays.toString(one));
        System.out.println(Arrays.toString(two));
        System.out.println(Arrays.toString(fullArray));

         /* Pseudocode:
         Create an integer index for `one`, `two`, and the result array, all starting at 0.
         Loop resultIndex from 0 to result.length - 1:
           if one[oneIndex] is less than two[twoIndex], add it to the result and increment oneIndex.
           if two[twoIndex] is less than one[oneIndex], add it to the result and increment twoIndex.
           determine how to settle ties
           if oneIndex >= one.length, there are no `one` elements remaining so use elements from two
           if twoIndex >= two.length, there are no `two` elements remaining so use elements from one
          */
    }

    public static int[] MakeRandomAscendingArray()
    {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        int current = random.nextInt(11);
        for (int i = 0; i < result.length; i++) {
            result[i] = current;
            current += random.nextInt(4);
        }
        return result;
    }
}
