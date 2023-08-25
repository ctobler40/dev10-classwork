package learn.boardgames.data;

import learn.boardgames.models.BoardGame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BoardGameRepository {

    List<BoardGame> findAll() throws DataAccessException;
    BoardGame findById(int id) throws DataAccessException;
    List<BoardGame> findByTitle(String title) throws DataAccessException;
    BoardGame add(BoardGame boardGame) throws DataAccessException;
    boolean update(BoardGame boardGame) throws DataAccessException;
    boolean deleteById(int id) throws DataAccessException;

}
