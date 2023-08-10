public class Exercise06
{
    public static void main(String[] args)
    {
        int[] values = {18, 42, 54, 93, 22};

        // 1. Create a loop to calculate the sum of elements in `values`.
        int sum = 0;
        for (int value : values) sum += value;

        // 2. Print the result.
        System.out.println(sum);
    }
}
