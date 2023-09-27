package learn.boardgames.models;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private LocalDate retailReleaseDate;
    // less than equal to 10 and greater than equal to 0
    private double rating;
    private boolean inCollection;
    // should not be null
    private Availability availability;

    // Pros and cons of BigDecimal vs floating point numbers
    // BigDecimal is useful for precise calculations with very small and large numbers
    private BigDecimal msrp;

    private String imageUrl;

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

    public LocalDate getRetailReleaseDate() {
        return retailReleaseDate;
    }

    public void setRetailReleaseDate(LocalDate retailReleaseDate) {
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

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardGame boardGame = (BoardGame) o;

        if (id != boardGame.id) return false;
        if (minimumPlayers != boardGame.minimumPlayers) return false;
        if (maximumPlayers != boardGame.maximumPlayers) return false;
        if (Double.compare(rating, boardGame.rating) != 0) return false;
        if (inCollection != boardGame.inCollection) return false;
        if (!Objects.equals(title, boardGame.title)) return false;
        if (!Objects.equals(retailReleaseDate, boardGame.retailReleaseDate))
            return false;
        if (availability != boardGame.availability) return false;
        if (!Objects.equals(msrp, boardGame.msrp)) return false;
        return Objects.equals(imageUrl, boardGame.imageUrl);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + minimumPlayers;
        result = 31 * result + maximumPlayers;
        result = 31 * result + (retailReleaseDate != null ? retailReleaseDate.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (inCollection ? 1 : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        result = 31 * result + (msrp != null ? msrp.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minimumPlayers=" + minimumPlayers +
                ", maximumPlayers=" + maximumPlayers +
                ", retailReleaseDate=" + retailReleaseDate +
                ", rating=" + rating +
                ", inCollection=" + inCollection +
                ", availability=" + availability +
                ", msrp=" + msrp +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
