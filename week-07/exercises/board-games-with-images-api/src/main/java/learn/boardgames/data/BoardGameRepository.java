package learn.boardgames.data;

import learn.boardgames.models.BoardGame;

import java.util.List;

public interface BoardGameRepository {

    List<BoardGame> findAll();

    BoardGame findById(int id);

    List<BoardGame> findByTitle(String title);

    BoardGame add(BoardGame boardGame);

    boolean update(BoardGame boardGame);

    boolean deleteById(int id);

}
