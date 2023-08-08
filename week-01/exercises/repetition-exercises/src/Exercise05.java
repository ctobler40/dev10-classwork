public class Exercise05
{
    public static void main(String[] args)
    {
        int index = 5;
        while (index <= 100)
        {
            System.out.println(index);
            index += 5;
        }

        // 1. Rewrite the following loop as a `for` statement.
        // Run the code before you make changes to better understand current behavior.
        // The transformation from `while` to `for` should not change behavior.
        for (int x = index; x <= 100; x += 5)
            System.out.println(x);
    }
}
