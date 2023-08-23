package learn.data;
import learn.models.BoardGame;
import java.util.List;

public class BoardGameFileRepository implements BoardGameRepository
{
    private final String filePath;
    public BoardGameFileRepository(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public List<BoardGame> findAll() {
        return null;
    }

    @Override
    public BoardGame findByID(int id) {
        return null;
    }

    @Override
    public BoardGame findByTitle(String name) {
        return null;
    }

    @Override
    public BoardGame add(BoardGame boardGame) {
        return null;
    }

    @Override
    public boolean update(BoardGame boardGame) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
