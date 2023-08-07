public class Exercise20
{
    public static void main(String[] args)
    {
        // From Alice Roosevelt Longworth:
        String aliceQuote = "If you can't say something good about someone, sit right here by me.";

        char tenthChar = aliceQuote.charAt(9);
        System.out.println(tenthChar);

        // 1. Store the first character from aliceQuote in a char variable.
        char firstChar = aliceQuote.charAt(0);

        // 2. Print it.
        System.out.println("1st Character: " + firstChar);

        // 3. Print the 20th character.
        System.out.println("20th Character: " + aliceQuote.charAt(19));

        // 4. Print the 68th character.
        System.out.println("68th Character: " + aliceQuote.charAt(67));
    }
}