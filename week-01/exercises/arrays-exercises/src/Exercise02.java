public class Exercise02
{
    public static void main(String[] args)
    {
        String[] tenFavoriteFoods = new String[10]; // space for 10 favorite foods

        // 1. Set your 10 favorite foods. (It's okay to replace squid ink.)
        tenFavoriteFoods[0] = "Donuts";
        tenFavoriteFoods[1] = "Chicken";
        tenFavoriteFoods[2] = "Rice";
        tenFavoriteFoods[3] = "Pizza";
        tenFavoriteFoods[4] = "Oranges";
        tenFavoriteFoods[5] = "Apples";
        tenFavoriteFoods[6] = "Steak";
        tenFavoriteFoods[7] = "Pork";
        tenFavoriteFoods[8] = "Broccoli";
        tenFavoriteFoods[9] = "Brussel Sprouts";

        System.out.println(tenFavoriteFoods[5]);
        System.out.println(tenFavoriteFoods[6]);

        // 2. Print your top, 6th, and last favorite from tenFavoriteFoods.
        for (int i = 0; i < tenFavoriteFoods.length; i++)
        {
            if (i == 0 || i == 5 || i == tenFavoriteFoods.length - 1)
                System.out.println(tenFavoriteFoods[i]);
        }
    }
}
