package learn.models;

import java.util.Objects;

public class BoardGame
{
    // Think of models as like containers for information
    // Consisting mainly of fields, getters and setters
    private int id;   // Internal way to reference individual games
    private String title;
    private int minimumPlayers;
    private int maximumPlayers;
    private String retailReleaseDate;
    private double rating;
    private boolean inCollection;
    private Availability availability;

    public BoardGame()
    {

    }

    public BoardGame(int id, String title, int minimumPlayers, int maximumPlayers, String retailReleaseDate,
                     double rating, boolean inCollection, Availability availability)
    {
        this.id = id;
        this.title = title;
        this.minimumPlayers = minimumPlayers;
        this.maximumPlayers = maximumPlayers;
        this.retailReleaseDate = retailReleaseDate;
        this.rating = rating;
        this.inCollection = inCollection;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinimumPlayers() {
        return minimumPlayers;
    }

    public void setMinimumPlayers(int minimumPlayers) {
        this.minimumPlayers = minimumPlayers;
    }

    public int getMaximumPlayers() {
        return maximumPlayers;
    }

    public void setMaximumPlayers(int maximumPlayers) {
        this.maximumPlayers = maximumPlayers;
    }

    public String getRetailReleaseDate() {
        return retailReleaseDate;
    }

    public void setRetailReleaseDate(String retailReleaseDate) {
        this.retailReleaseDate = retailReleaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isInCollection() {
        return inCollection;
    }

    public void setInCollection(boolean inCollection) {
        this.inCollection = inCollection;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return id == boardGame.id && minimumPlayers == boardGame.minimumPlayers && maximumPlayers == boardGame.maximumPlayers && Double.compare(rating, boardGame.rating) == 0 && inCollection == boardGame.inCollection && Objects.equals(title, boardGame.title) && Objects.equals(retailReleaseDate, boardGame.retailReleaseDate) && availability == boardGame.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, minimumPlayers, maximumPlayers, retailReleaseDate, rating, inCollection, availability);
    }
}
