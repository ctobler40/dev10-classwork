package learn.boardgames.data;

import learn.boardgames.models.Availability;
import learn.boardgames.models.BoardGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameFileRepositoryTest {

    private static final String SEED_FILE_PATH = "data/board-games-seed.csv";
    private static final String TEST_FILE_PATH = "data/board-games-test.csv";

    BoardGameFileRepository repository = new BoardGameFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAllBoardGames() throws DataAccessException {
        List<BoardGame> boardGames = List.of(
                new BoardGame(1,"Lunar Rush",1,4,
                        "2023-10-20",8.5,true, Availability.UPCOMING),
                new BoardGame(2,"Ascension",1,4,
                        "2010-01-01",7.0,false,Availability.AVAILABLE_IN_RETAIL),
                new BoardGame(3,"Carcassonne",2,5,
                        "2000-03-10",7.4,true,Availability.OUT_OF_PRINT)
        );

        List<BoardGame> actual = repository.findAll();

        assertEquals(boardGames, actual);
    }

    @Test
    void shouldFindLunarRushById() throws DataAccessException {
        BoardGame lunarRush = new BoardGame(1,"Lunar Rush",1,4,
                "2023-10-20",8.5,true, Availability.UPCOMING);

        BoardGame actual = repository.findById(1);

        assertEquals(lunarRush, actual);
    }

    @Test
    void shouldFindAscensionByTitle() throws DataAccessException {
        List<BoardGame> boardGamesWithTitle = List.of(
                new BoardGame(2,"Ascension",1,4,
                        "2010-01-01",7.0,false,Availability.AVAILABLE_IN_RETAIL));

        List<BoardGame> actual = repository.findByTitle("Ascension");

        assertEquals(boardGamesWithTitle, actual);
    }

    @Test
    void shouldFindNothingByTitleWithMissingTitle() throws DataAccessException {
        assertEquals(0, repository.findByTitle("NonsenseNot exists").size());
    }

    @Test
    void shouldNotFindNotExistingGameById() throws DataAccessException {
        BoardGame actual = repository.findById(99);
        assertNull(actual);
    }

    @Test
    void shouldAddBoardGame() throws DataAccessException {
        BoardGame boardGame = new BoardGame(0, "Munchkin, the Game", 3, 6,
                "2001-12-24", 6.9, true, Availability.AVAILABLE_IN_RETAIL);

        BoardGame expected = new BoardGame(4, "Munchkin, the Game", 3, 6,
                "2001-12-24", 6.9, true, Availability.AVAILABLE_IN_RETAIL);

        BoardGame actual = repository.add(boardGame);

        assertEquals(expected, actual);

        BoardGame addedGame = repository.findById(4);

        assertEquals(expected, addedGame);
    }

    @Test
    void shouldUpdateLunarRush() throws DataAccessException {
        BoardGame boardGame = new BoardGame(1,"Lunar Rush",1,5,
                "2023-10-20",8.6,true, Availability.AVAILABLE_IN_RETAIL);

        assertTrue(repository.update(boardGame));

        assertEquals(boardGame, repository.findById(1));
    }

    @Test
    void shouldNotUpdateNotExistingBoardGame() throws DataAccessException {
        BoardGame boardGame = new BoardGame(99,"Not Real Game",1,5,
                "2023-10-20",8.6,true, Availability.AVAILABLE_IN_RETAIL);

        assertFalse(repository.update(boardGame));
    }

    @Test
    void shouldDeleteAscensionById() throws DataAccessException {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }
}