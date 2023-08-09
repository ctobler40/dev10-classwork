public class Exercise06
{
    // 1. Create a method.
    // Name: isBetween
    // Inputs: int, int, int
    // Output: boolean
    // Description: return true if the first parameter is between the second and third parameter.
    // Otherwise, returns false.

    public static void main(String[] args)
    {
        // 2. Call your method in various ways to test it here.
        System.out.println(isBetween(5, 1, 10));
        System.out.println(isBetween(1, 5, 10));
        System.out.println(isBetween(10, 1, 5));
    }

    public static boolean isBetween(int check, int smaller, int larger)
    {
        return (check > smaller && check < larger);
    }
}
