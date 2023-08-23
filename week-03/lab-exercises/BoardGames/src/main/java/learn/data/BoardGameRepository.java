package learn.data;
import learn.models.BoardGame;
import java.util.List;

public interface BoardGameRepository
{
    List<BoardGame> findAll() throws DataAccessException;   // Get all board games
    BoardGame findByID(int id) throws DataAccessException;   // Get 1 board game based on id
    BoardGame findByTitle(String name) throws DataAccessException;   // Get 1 board game based on title
    BoardGame add(BoardGame boardGame);   // Add game to list
    boolean update(BoardGame boardGame);
    boolean deleteById(int id);   // Remove board game based on ID
}