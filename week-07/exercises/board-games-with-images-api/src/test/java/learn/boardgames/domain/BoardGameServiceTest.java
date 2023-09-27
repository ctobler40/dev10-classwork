package learn.boardgames.domain;

import learn.boardgames.data.BoardGameRepository;
import learn.boardgames.models.BoardGame;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static learn.boardgames.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BoardGameServiceTest {

    @MockBean
    BoardGameRepository repository;

    @Autowired
    BoardGameService service;

    @Test
    void shouldFindByTitle() {
        List<BoardGame> expected = List.of(makeBoardGame(1));

        when(repository.findByTitle(any())).thenReturn(List.of(makeBoardGame(1)));

        List<BoardGame> actual = service.findByTitle("Title #1");
        assertEquals(expected, actual);
    }

    // Add

    @Test
    void shouldNotAddNullGame() {
        BoardGameResult expected = makeResult("game cannot be null", null);
        BoardGameResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullTitle() {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(1);
        arg.setTitle(null);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddBlankTitle() {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(1);
        arg.setTitle(" \n\t");
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddMinPlayerLessEqual0() {
        BoardGameResult expected = makeResult("min players must be greater than 0", null);
        BoardGame arg = makeBoardGame(1);
        arg.setMinimumPlayers(0);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddMaxPlayerLessMinPlayer() {
        BoardGameResult expected = makeResult("max players must be greater than or equal to min players", null);
        BoardGame arg = makeBoardGame(1);
        arg.setMaximumPlayers(0);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddRatingOutOfRange() {
        BoardGameResult expected = makeResult("rating must be between 0 and 10", null);
        BoardGame arg = makeBoardGame(1);
        arg.setRating(10.1);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullAvailability() {
        BoardGameResult expected = makeResult("availability is required", null);
        BoardGame arg = makeBoardGame(1);
        arg.setAvailability(null);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullRetailReleaseDate() {
        BoardGameResult expected = makeResult("release date is required", null);
        BoardGame arg = makeBoardGame(1);
        arg.setRetailReleaseDate(null);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateGame() {
        BoardGameResult expected = makeResult("duplicate game", null);
        BoardGame arg = makeBoardGame(1);
        arg.setId(0);

        when(repository.findByTitle(any())).thenReturn(List.of(makeBoardGame(1)));

        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullMsrp() {
        BoardGameResult expected = makeResult("MSRP is required", null);
        BoardGame arg = makeBoardGame(1);
        arg.setMsrp(null);
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNegativeMsrp() {
        BoardGameResult expected = makeResult("MSRP cannot be negative", null);
        BoardGame arg = makeBoardGame(1);
        arg.setMsrp(new BigDecimal("-100.99"));
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidUrl() {
        BoardGameResult expected = makeResult("not a valid URL", null);
        BoardGame arg = makeBoardGame(1);
        arg.setImageUrl("not-a-url");
        BoardGameResult actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        BoardGameResult expected = makeResult(null, makeBoardGame(1));
        BoardGame arg = makeBoardGame(1);

        when(repository.add(any())).thenReturn(makeBoardGame(1));

        BoardGameResult actual = service.add(arg);

        assertEquals(expected, actual);
    }

    @Test
    void shouldMock() {
        BoardGame game = mock(BoardGame.class);
        boolean isInstance = game instanceof BoardGame;
        System.out.println();
    }

    // Update

    @Test
    void shouldNotUpdateNullGame() {
        BoardGameResult expected = makeResult("game cannot be null", null);
        BoardGameResult actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullTitle() {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setTitle(null);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateBlankTitle() {
        BoardGameResult expected = makeResult("game title cannot be blank", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setTitle(" \n\t");
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMinPlayerLessEqual0() {
        BoardGameResult expected = makeResult("min players must be greater than 0", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setMinimumPlayers(0);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMaxPlayerLessMinPlayer() {
        BoardGameResult expected = makeResult("max players must be greater than or equal to min players", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setMaximumPlayers(0);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateRatingOutOfRange() {
        BoardGameResult expected = makeResult("rating must be between 0 and 10", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setRating(10.1);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullAvailability() {
        BoardGameResult expected = makeResult("availability is required", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setAvailability(null);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullRetailReleaseDate() {
        BoardGameResult expected = makeResult("release date is required", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setRetailReleaseDate(null);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateLessThan0Id() {
        BoardGameResult expected = makeResult("game id is required", null);
        BoardGame arg = makeBoardGame(VALID_ID);
        arg.setId(0);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateDuplicateGame() {
        BoardGameResult expected = makeResult("duplicate game", null);
        BoardGame arg = makeBoardGame(1);
        arg.setId(2);

        when(repository.findByTitle(any())).thenReturn(List.of(makeBoardGame(1)));

        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissing() {
        BoardGameResult expected = makeResult("could not update game id 3", null);
        BoardGame arg = makeBoardGame(3);
        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() {
        BoardGameResult expected = makeResult(null, null);
        BoardGame arg = makeBoardGame(VALID_ID);

        when(repository.update(any())).thenReturn(true);

        BoardGameResult actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // Delete

    @Test
    void shouldNotDeleteMissing() {
        BoardGameResult expected = makeResult("could not delete game id 3", null);
        BoardGameResult actual = service.deleteById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDelete() {
        BoardGameResult expected = makeResult(null, null);

        when(repository.deleteById(anyInt())).thenReturn(true);

        BoardGameResult actual = service.deleteById(VALID_ID);
        assertEquals(expected, actual);
    }

    // TODO: update msrp tests, add test for report

}