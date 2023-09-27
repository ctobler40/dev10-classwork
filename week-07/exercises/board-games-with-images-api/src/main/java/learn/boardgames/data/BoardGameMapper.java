package learn.boardgames.data;

import learn.boardgames.models.Availability;
import learn.boardgames.models.BoardGame;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardGameMapper implements RowMapper<BoardGame> {
    @Override
    public BoardGame mapRow(ResultSet rs, int rowNum) throws SQLException {

        BoardGame game = new BoardGame();
        game.setId(rs.getInt("board_game_id"));
        game.setTitle(rs.getString("title"));
        game.setMinimumPlayers(rs.getInt("min_players"));
        game.setMaximumPlayers(rs.getInt("max_players"));
        game.setRetailReleaseDate(rs.getDate("release_date").toLocalDate());
        game.setRating(rs.getDouble("rating"));
        game.setInCollection(rs.getBoolean("in_collection"));
        game.setAvailability(Availability.valueOf(rs.getString("availability")));
        game.setMsrp(rs.getBigDecimal("msrp"));
        game.setImageUrl(rs.getString("image_url"));
        return game;
    }
}
