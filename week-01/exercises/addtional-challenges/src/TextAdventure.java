import java.util.Arrays;

public class TextAdventure
{
    public static void main(String [] args)
    {
        String str = "Kenobi";
        System.out.println(str.length());
        System.out.println(Arrays.toString(str.split("o")));
        System.out.println(str.toLowerCase());
        System.out.println(str.toUpperCase());
        System.out.println(str.lines());
        System.out.println(str.strip());
        System.out.println(str.toCharArray());
        System.out.println(str.indexOf('o'));
    }
}
