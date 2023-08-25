package learn.boardgames.data;

import learn.boardgames.models.BoardGame;

import java.util.List;

import static learn.boardgames.TestHelper.makeBoardGame;
import static learn.boardgames.TestHelper.VALID_ID;

public class BoardGameRepositoryDouble implements BoardGameRepository {

    @Override
    public List<BoardGame> findAll() throws DataAccessException {
        return null;
    }

    @Override
    public BoardGame findById(int id) throws DataAccessException {
        return makeBoardGame(VALID_ID);
    }

    @Override
    public List<BoardGame> findByTitle(String title) throws DataAccessException {
        return List.of(makeBoardGame(1));
    }

    @Override
    public BoardGame add(BoardGame boardGame) throws DataAccessException {
        return boardGame;
    }

    @Override
    public boolean update(BoardGame boardGame) throws DataAccessException {
        return boardGame.getId() == VALID_ID;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        return id == VALID_ID;
    }
}
