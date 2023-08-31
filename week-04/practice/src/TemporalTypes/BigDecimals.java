package TemporalTypes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimals
{
    public static void main(String[] args)
    {
        BigDecimal a = new BigDecimal("10.5");
        BigDecimal b = a.add(new BigDecimal("2.5"));
        System.out.println(a + "\n" + b);

        System.out.println();
        BigDecimal c = new BigDecimal("10.5");
        BigDecimal d = c.divide(new BigDecimal("2.5"));
        System.out.println(c + "\n" + d);

        System.out.println();
        roundingModes();
    }

    public static void roundingModes()
    {
        BigDecimal value = new BigDecimal("1.13");
        BigDecimal twoHundredths = new BigDecimal("0.02");
        BigDecimal threshold = new BigDecimal("2.0");
        System.out.println("VALUE |  UP  | DOWN | CEILING | FLOOR | HALF_UP | HALF_DOWN | HALF_EVEN");
        for (; value.compareTo(threshold) < 0; value = value.add(twoHundredths)) {
            System.out.printf("%s  |  %s | %s  | %s     | %s   | %s     | %s       | %s%n",
                    value,
                    value.setScale(1, RoundingMode.UP),
                    value.setScale(1, RoundingMode.DOWN),
                    value.setScale(1, RoundingMode.CEILING),
                    value.setScale(1, RoundingMode.FLOOR),
                    value.setScale(1, RoundingMode.HALF_UP),
                    value.setScale(1, RoundingMode.HALF_DOWN),
                    value.setScale(1, RoundingMode.HALF_EVEN)
            );
        }

    }
}