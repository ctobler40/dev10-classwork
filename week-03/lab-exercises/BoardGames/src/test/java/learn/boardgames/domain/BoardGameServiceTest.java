package learn.boardgames.domain;

import learn.boardgames.data.BoardGameRepositoryDouble;
import learn.boardgames.data.DataAccessException;
import learn.boardgames.models.BoardGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static learn.boardgames.TestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardGameServiceTest {

    BoardGameService service;

    @BeforeEach
    void setup() {
        BoardGameRepositoryDouble repository = new BoardGameRepositoryDouble();
        service = new BoardGameService(repository);
    }

    @Test
    void shouldFindByTitle() throws DataAccessException {
        List<BoardGame> expected = List.of(makeBoardGame(1));
        List<BoardGame> actual = service.findByTitle("Title #1");
        assertEquals(expected, actual);
    }

    // Add

    @Test
    void shouldNotAddNullGame() throws DataAccessException {
        BoardGameResult expected = makeResult("game cannot be null", null);
        BoardGameResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullTitle() throws DataAccessException {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(1);
        arg.setTitle(null);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddBlankTitle() throws DataAccessException {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(1);
        arg.setTitle(" \n\t");
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddMinPlayerLessEqual0() throws DataAccessException {
        BoardGameResult expected = makeResult("min players must be greater than 0", null);
        BoardGame arg = makeBoardGame(1);
        arg.setMinimumPlayers(0);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddMaxPlayerLessMinPlayer() throws DataAccessException {
        BoardGameResult expected = makeResult("max players must be greater than or equal to min players", null);
        BoardGame arg = makeBoardGame(1);
        arg.setMaximumPlayers(0);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddRatingOutOfRange() throws DataAccessException {
        BoardGameResult expected = makeResult("rating must be between 0 and 10", null);
        BoardGame arg = makeBoardGame(1);
        arg.setRating(10.1);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullAvailability() throws DataAccessException {
        BoardGameResult expected = makeResult("availability is required", null);
        BoardGame arg = makeBoardGame(1);
        arg.setAvailability(null);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullRetailReleaseDate() throws DataAccessException {
        BoardGameResult expected = makeResult("release date is required", null);
        BoardGame arg = makeBoardGame(1);
        arg.setRetailReleaseDate(null);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddBadFormatRetailReleaseDate() throws DataAccessException {
        BoardGameResult expected = makeResult("release date has wrong format. required: yyyy-MM-dd", null);
        BoardGame arg = makeBoardGame(1);
        arg.setRetailReleaseDate("nope");
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);

        arg.setRetailReleaseDate("2222-44-4444");
        actual = service.add(arg);
        assertEquals(expected, actual);

        arg.setRetailReleaseDate("23-01-05");
        actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateGame() throws DataAccessException {
        BoardGameResult expected = makeResult("duplicate game", null);
        BoardGame arg = makeBoardGame(1);
        arg.setId(0);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        BoardGameResult expected = makeResult(null, makeBoardGame(1));
        BoardGame arg = makeBoardGame(1);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // Update

    @Test
    void shouldNotUpdateNullGame() throws DataAccessException {
        BoardGameResult expected = makeResult("game cannot be null", null);
        BoardGameResult actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullTitle() throws DataAccessException {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setTitle(null);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateBlankTitle() throws DataAccessException {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setTitle(" \n\t");
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMinPlayerLessEqual0() throws DataAccessException {
        BoardGameResult expected = makeResult("min players must be greater than 0", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setMinimumPlayers(0);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMaxPlayerLessMinPlayer() throws DataAccessException {
        BoardGameResult expected = makeResult("max players must be greater than or equal to min players", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setMaximumPlayers(0);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateRatingOutOfRange() throws DataAccessException {
        BoardGameResult expected = makeResult("rating must be between 0 and 10", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setRating(10.1);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullAvailability() throws DataAccessException {
        BoardGameResult expected = makeResult("availability is required", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setAvailability(null);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullRetailReleaseDate() throws DataAccessException {
        BoardGameResult expected = makeResult("release date is required", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setRetailReleaseDate(null);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateBadFormatRetailReleaseDate() throws DataAccessException {
        BoardGameResult expected = makeResult("release date has wrong format. required: yyyy-MM-dd", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setRetailReleaseDate("nope");
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);

        arg.setRetailReleaseDate("2222-44-4444");
        actual = service.update(arg);
        assertEquals(expected, actual);

        arg.setRetailReleaseDate("23-01-05");
        actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateLessThan0Id() throws DataAccessException {
        BoardGameResult expected = makeResult("game id is required", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setId(0);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateDuplicateGame() throws DataAccessException {
        BoardGameResult expected = makeResult("duplicate game", null);
        BoardGame arg = makeBoardGame(1);
        arg.setId(2);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissing() throws DataAccessException {
        BoardGameResult expected = makeResult("could not update game id 3", null);
        BoardGame arg = makeBoardGame(3);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        BoardGameResult expected = makeResult(null, null);
        BoardGame arg = makeBoardGame(VALID_ID);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // Delete

    @Test
    void shouldNotDeleteMissing() throws DataAccessException {
        BoardGameResult expected = makeResult("could not delete game id 3", null);
        BoardGameResult actual = service.deleteById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDelete() throws DataAccessException {
        BoardGameResult expected = makeResult(null, null);
        BoardGameResult actual = service.deleteById(VALID_ID);
        assertEquals(expected, actual);
    }

}