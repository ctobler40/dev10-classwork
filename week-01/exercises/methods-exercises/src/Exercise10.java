public class Exercise10
{
    // 1. Add a `main` method.
    public static void main(String[] args)
    {
        // 3. Call your method in various ways in the main method.
        RemoveWhitespaces("Hello There General Kenobi");
    }

    // 2. Create method that accepts a String and returns that string with all of its whitespace remove.
    public static void RemoveWhitespaces(String string)
    {
        String result = "";
        for (int i = 0; i < string.length(); i++)
        {
            if (!Character.isWhitespace(string.charAt(i)))
                result += string.charAt(i);
        }
        System.out.println(result);
    }
}
