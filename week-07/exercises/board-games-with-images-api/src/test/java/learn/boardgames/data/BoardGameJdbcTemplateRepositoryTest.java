package learn.boardgames.data;

import learn.boardgames.models.BoardGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static learn.boardgames.TestHelper.makeBoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BoardGameJdbcTemplateRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BoardGameJdbcTemplateRepository repository;

    @BeforeEach
    void setup() {
        jdbcTemplate.execute("call set_known_good_state();");
    }

    @Test
    void shouldFindAll() {
        List<BoardGame> expected = List.of(makeBoardGame(1),
                makeBoardGame(2), makeBoardGame(3));
        List<BoardGame> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById_2() {
        BoardGame expected = makeBoardGame(2);
        BoardGame actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindById_12() {
        assertNull(repository.findById(12));
    }


    @Test
    void shouldAdd() {
        BoardGame arg = makeBoardGame(4);
        arg.setId(0);
        BoardGame expected = makeBoardGame(4);
        BoardGame actual = repository.add(arg);
        assertEquals(expected, actual);

        actual = repository.findById(4);
        assertEquals(expected, actual);
    }

}