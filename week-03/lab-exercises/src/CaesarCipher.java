import java.util.Objects;

public class CaesarCipher
{
    static String letters = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args)
    {
        String[] test =
        {
            "how much wood could a wood chuck chuck if a wood chuck could chuck wood",
            "she sells sea shells down by the sea shore",
            "the bears are a much better team than the packers",
            "i love bacon, beer, birds, and baboons",
            "douglas figured the best way to succeed was to do the opposite of what he'd been doing all his life",
            "greetings from the real universe",
            "the two walked down the slot canyon oblivious to the sound of thunder in the distance",
            "strawberries must be the one food that doesn't go well with this brand of paint",
            "she used her own hair in the soup to give it more flavor",
            "the sunblock was handed to the girl before practice, but the burned skin was proof she did not apply it"
        };

        System.out.println("---------------------------------------");
        System.out.println("Encode");
        System.out.println("---------------------------------------");
        for (String s : test) System.out.println(encode(s));
        System.out.println("---------------------------------------");
        System.out.println("Decode");
        System.out.println("---------------------------------------");
        for (String s : test) System.out.println(decode(encode(s)));
        System.out.println("---------------------------------------");
    }

    public static String encode(String plainText)
    {
        String[] plainTextArray = plainText.toLowerCase().split("");
        for (int x = 0; x < plainTextArray.length; x ++)
        {
            if (!Objects.equals(plainTextArray[x], " "))
                plainTextArray[x] = Character.toString(letters.charAt((letters.indexOf(plainText.charAt(x)) + 3) % 26));
        }
        return String.join("", plainTextArray);
    }

    public static String decode(String plainText)
    {
        String[] plainTextArray = plainText.toLowerCase().split("");
        for (int x = 0; x < plainTextArray.length; x ++)
        {
            if (!Objects.equals(plainTextArray[x], " "))   // This part was not completed
                plainTextArray[x] = Character.toString(letters.charAt((letters.indexOf(plainText.charAt(x)) - 3) % 26));
        }
        return String.join("", plainTextArray);
    }
}
