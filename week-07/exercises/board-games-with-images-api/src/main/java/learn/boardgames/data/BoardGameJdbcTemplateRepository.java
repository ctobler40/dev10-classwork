package learn.boardgames.data;

import learn.boardgames.models.BoardGame;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BoardGameJdbcTemplateRepository implements BoardGameRepository {

    private static final String SELECT = """
            select board_game_id, title, min_players, max_players,
            release_date, rating, in_collection, availability, msrp,
            image_url
            from board_game
            """;
    private final JdbcTemplate jdbcTemplate;

    public BoardGameJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BoardGame> findAll() {
        String sql = SELECT + ";";
        return jdbcTemplate.query(sql, new BoardGameMapper());
    }

    @Override
    public BoardGame findById(int id) {
        String sql = SELECT + "where board_game_id = ?;";

        return jdbcTemplate.query(sql, new BoardGameMapper(), id).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<BoardGame> findByTitle(String title) {
        String sql = SELECT + "where title LIKE ?;";

        return jdbcTemplate.query(sql, new BoardGameMapper(), title + "%");
    }

    @Override
    public BoardGame add(BoardGame boardGame) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("board_game")
                .usingGeneratedKeyColumns("board_game_id");

        HashMap<String, Object> args = new HashMap<>();
        args.put("title", boardGame.getTitle());
        args.put("min_players", boardGame.getMinimumPlayers());
        args.put("max_players", boardGame.getMaximumPlayers());
        args.put("release_date", boardGame.getRetailReleaseDate());
        args.put("rating", boardGame.getRating());
        args.put("in_collection", boardGame.isInCollection());
        args.put("availability", boardGame.getAvailability());
        args.put("msrp", boardGame.getMsrp());
        args.put("image_url", boardGame.getImageUrl());

        int id = insert.executeAndReturnKey(args).intValue();
        boardGame.setId(id);

        return boardGame;
    }

    @Override
    public boolean update(BoardGame boardGame) {
        String sql = """
                update board_game set
                    title = ?,
                    min_players = ?,
                    max_players = ?,
                    release_date = ?,
                    rating = ?,
                    in_collection = ?,
                    availability = ?,
                    msrp = ?,
                    image_url = ?
                where board_game_id = ?;
                """;

        return jdbcTemplate.update(sql,
                boardGame.getTitle(),
                boardGame.getMinimumPlayers(),
                boardGame.getMaximumPlayers(),
                boardGame.getRetailReleaseDate(),
                boardGame.getRating(),
                boardGame.isInCollection(),
                boardGame.getAvailability().toString(),
                boardGame.getMsrp(),
                boardGame.getImageUrl(),
                boardGame.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("delete from board_game where board_game_id = ?;", id) > 0;
    }
}
