public class Exercise04
{
    public static void main(String[] args)
    {
        System.out.println(getFirstVowel("magnificent")); // Expected: a
        System.out.println(getFirstVowel("winsome")); // Expected: i
        System.out.println(getFirstVowel("xxx")); // Expected:

        // 2. Call getFirstVowel at least one more time.
        System.out.println(getFirstVowel("elegant"));
    }

    // getFirstVowel returns the first vowel in a string as a char.
    // 1. Complete getFirstVowel.
    // If no vowel is found, return 0. (As a char, 0 represents the NULL value.)
    public static char getFirstVowel(String value)
    {
        int charPos = -1;
        for (int x = 0; x < value.length(); x ++)
        {
            if (value.charAt(x) == 'a' || value.charAt(x) == 'e' || value.charAt(x) == 'i' || value.charAt(x) == 'o' || value.charAt(x) == 'u')
            {
                charPos = x;
                break;
            }
        }
        if (charPos != -1)
            return value.charAt(charPos);
        return '#';
    }
}
