import java.util.Arrays;

public class RNA
{
    public static void main(String[] args)
    {
        /*
         * "RNA encoding"
         * Write a method that when given a sequence of RNA bases represented as a string with characters A, U, C, G,
         * it returns the transcribed RNA strand
         * A pairs with U
         * C pairs with G
         * Examples
            * "AACG" -> "UUGC"
            * "GGAAUU" -> "CCUUAA"
            * "GCUA" -> "CGAU"
            * Call the method multiple times to ensure that it's working correctly
         * Use the IntelliJ debugger to step through your code
         * Can you write a method that accepts two strings compares them and outputs a message about the equality
         * of the two strings to help facilitate testing?
         */
        String sequence = "ACCUUGGAUGCA";
        String sequence2 = "ACCUGGACAUCG";
        System.out.println(rna(sequence));
        System.out.println(rna2(sequence));
        System.out.println(compareRnas(sequence, sequence2));
    }

    public static String rna2(String baseString)
    {
        String[] stripped = baseString.split("");
        for (int i = 0; i < stripped.length; i++)
        {
            switch (stripped[i])
            {
                case "A":
                    stripped[i] = "U";
                    break;
                case "U":
                    stripped[i] = "A";
                    break;
                case "C":
                    stripped[i] = "G";
                    break;
                case "G":
                    stripped[i] = "C";
                    break;
                default:
                    return "Error: Improper String";
            }
        }
        return Arrays.toString(stripped);
    }

    public static String rna(String baseString)
    {
        // NOTE: A StringBuilder eats up less storage
        StringBuilder newRna = new StringBuilder("");
        for (int i = 0; i < baseString.length(); i++)
        {
             char rnaLetter = baseString.charAt(i);
             switch (rnaLetter)
             {
                 case 'A':
                     newRna.append('U');
                     break;
                 case 'U':
                     newRna.append('A');
                     break;
                 case 'C':
                     newRna.append('G');
                     break;
                 case 'G':
                     newRna.append('C');
                     break;
                 default:
                     return "Error: Improper String";
             }
        }

        return newRna.toString();
    }

    public static String compareRnas(String string1, String string2)
    {
        return string1.matches(string2) ? "Pass" : "Fail";
    }
}