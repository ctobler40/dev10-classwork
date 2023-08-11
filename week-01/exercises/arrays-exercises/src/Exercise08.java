import java.util.Random;

public class Exercise08
{
    public static void main(String[] args)
    {
        int[] values = MakeRandomArray();
        // 2. Uncomment the code below and make it work.
        int sum = SumArray(values);
        System.out.println(sum);
        System.out.println(SumArray(MakeRandomArray()));
        System.out.println(SumArray(MakeRandomArray()));
    }

    // 1. Create a method.
    // Name: sumArray
    // Inputs: int[]
    // Output: int
    // Description: calculates the sum of the parameter's elements and returns it.
    public static int SumArray(int[] numArray)
    {
        int sum = 0;
        for (int j : numArray) sum += j;
        return sum;
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
