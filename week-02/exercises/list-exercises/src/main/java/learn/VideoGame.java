package learn;

import java.util.Objects;

public class VideoGame
{

    private final String name;
    private final int minPlayers;
    private final int maxPlayers;
    private final int hoursPlayed;

    public VideoGame(String name, int minPlayers, int maxPlayers, int hoursPlayed)
    {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.hoursPlayed = hoursPlayed;
    }

    public String getName() {
        return name;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoGame videoGame = (VideoGame) o;
        return minPlayers == videoGame.minPlayers &&
                maxPlayers == videoGame.maxPlayers &&
                Objects.equals(name, videoGame.name) &&
                Objects.equals(hoursPlayed, videoGame.hoursPlayed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, minPlayers, maxPlayers, hoursPlayed);
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "name='" + name + '\'' +
                ", minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", hoursPlayed='" + hoursPlayed + '\'' +
                '}';
    }
}
