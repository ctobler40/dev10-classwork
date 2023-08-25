package learn.boardgames.models;

import java.util.Objects;

public class BoardGame {
    private int id;
    // should not be null or blank
    private String title;
    // should be greater than equal to 1
    private int minimumPlayers;
    // should be grater than equal to min players
    private int maximumPlayers;
    // should sorta be a date or null
    private String retailReleaseDate;
    // less than equal to 10 and greater than equal to 0
    private double rating;
    private boolean inCollection;
    // should not be null
    private Availability availability;

    public BoardGame() {

    }

    public BoardGame(int id, String title, int minimumPlayers, int maximumPlayers,
                     String retailReleaseDate, double rating, boolean inCollection, Availability availability) {
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
        return id == boardGame.id && minimumPlayers == boardGame.minimumPlayers && maximumPlayers == boardGame.maximumPlayers && Double.compare(boardGame.rating, rating) == 0 && inCollection == boardGame.inCollection && Objects.equals(title, boardGame.title) && Objects.equals(retailReleaseDate, boardGame.retailReleaseDate) && availability == boardGame.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, minimumPlayers, maximumPlayers, retailReleaseDate, rating, inCollection, availability);
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minimumPlayers=" + minimumPlayers +
                ", maximumPlayers=" + maximumPlayers +
                ", retailReleaseDate='" + retailReleaseDate + '\'' +
                ", rating=" + rating +
                ", inCollection=" + inCollection +
                ", availability=" + availability +
                '}';
    }
}
