public class Exercise25
{
    // Mad Libs: https://en.wikipedia.org/wiki/Mad_Libs
    // 1. Add a main method.
    public static void main(String [] args)
    {
        // 2. Declare several variables of various types to be "plugged in" to a Mad Libs sentence.
        int numItems = 4, numPeople = 12, houseAddress = 6565, score = 7;
        String townName = "Waterloo", streetName = "Biggus", favoriteTeam = "Bears", leastFavoriteTeam = "Packers";
        float numSeconds = 5.5f;
        boolean yourTeamWinning = true;

        // 3. Use string concatenation to build a silly sentence.
        String madlibs = "I brought my " + numItems + " dogs to the house at " + houseAddress + " " + streetName +
                " to watch my favorite team, the " + favoriteTeam + " play against my least favorite team, the " +
                leastFavoriteTeam + ".\nThere were " + numPeople + " people here and I was the only one rooting for the " +
                favoriteTeam + ".\nWhich I guess makes sense being here in the town of " + townName + ".\nIt only took roughly " +
                numSeconds + " seconds before " + (yourTeamWinning ? "my" : "their") + " team already took the lead by scoring "
                + score + " points!\n" + (yourTeamWinning ? "I think I'm gonna stay..." : "I think I'm heading out early...");

        // 4. Print the result.
        System.out.println(madlibs);

        // 5. Change variable values to change the sentence. Ask a friend for random values to increase the chances
        // of something hilarious or kinda naughty.
        // Declaring it into a function
        System.out.println();
        System.out.println(madlibFunction(3, 25, 4565, 10, 10.5f, "Merfville",
                                    "Bisco Street", "Steelers", "Ravens", true));
        System.out.println();
        System.out.println(madlibFunction(6, 5, 8787, 14, 30.5f, "Smallville",
                "Fireball Street", "Cowboys", "Eagles", false));
        System.out.println();
        System.out.println(madlibFunction(2, 15, 1234, 3, 20.5f, "Lincoln",
                "Waterball Street", "Saints", "Partriots", true));
    }

    public static String madlibFunction(int numItems, int numPeople, int houseAddress, int score, float numSeconds, String townName,
                                        String streetName, String favoriteTeam, String leastFavoriteTeam, boolean yourTeamWinning)
    {
        return "I brought my " + numItems + " dogs to the house at " + houseAddress + " " + streetName +
                " to watch my favorite team, the " + favoriteTeam + " play against my least favorite team, the " +
                leastFavoriteTeam + ".\nThere were " + numPeople + " people here and I was the only one rooting for the " +
                favoriteTeam + ".\nWhich I guess makes sense being here in the town of " + townName + ".\nIt only took roughly " +
                numSeconds + " seconds before " + (yourTeamWinning ? "my" : "their") + " team already took the lead by scoring "
                + score + " points!\n" + (yourTeamWinning ? "I think I'm gonna stay..." : "I think I'm heading out early...");
    }
}
