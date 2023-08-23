package learn.data;
import learn.models.Availability;
import learn.models.BoardGame;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class BoardGameFileRepositoryTest
{
    // Make sure we are getting the correct string for file path location!
    BoardGameFileRepository repository = new BoardGameFileRepository("data/board-games-test.csv");

    @Test
    public void shouldFindAllBoardGames() throws DataAccessException
    {
        // This is what we are expecting
        List<BoardGame> expectedBoardGames = List.of(
                new BoardGame(1, "Lunar Rush", 1, 4, "2023/10/20", 8.5, true, Availability.UPCOMING),
                new BoardGame(2, "Ascension", 1, 4, "2010/05/14", 7.0, false, Availability.AVAILABLE_IN_RETAIL),
                new BoardGame(3, "Carcassonne", 2, 5, "2000/12/31", 7.4, true, Availability.OUT_OF_PRINT)
        );
        List<BoardGame> actualBoardGames = repository.findAll();
        assertEquals(expectedBoardGames, actualBoardGames);
    }

    @Test
    public void shouldFindLunarRushById() throws DataAccessException
    {
        BoardGame lunarRush = new BoardGame(1, "Lunar Rush", 1, 4, "2023/10/20", 8.5, true, Availability.UPCOMING);
        BoardGame actual = repository.findByID(1);
        assertEquals(lunarRush, actual);
    }

    @Test
    public void shouldFindLunarRushByTitle() throws DataAccessException
    {
        BoardGame lunarRush = new BoardGame(1, "Lunar Rush", 1, 4, "2023/10/20", 8.5, true, Availability.UPCOMING);
        BoardGame actual = repository.findByTitle("Lunar Rush");
        assertEquals(lunarRush, actual);
    }
}