import java.lang.reflect.Array;

public class Exercise04
{
    public static void main(String[] args)
    {
        // 1. Declare an array to hold the names of the world's oceans.
        // Set its value using array literal notation.
        String[] oceans = { "Pacific", "Atlantic", "Indian", "Arctic"};

        // 2. Loop over each element and print it.
        for (String ocean : oceans) System.out.println(ocean);
    }
}
