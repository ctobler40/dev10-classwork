package learn.data;
import learn.models.Availability;
import learn.models.BoardGame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardGameFileRepository implements BoardGameRepository
{
    private final String filePath;
    public BoardGameFileRepository(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public List<BoardGame> findAll() throws DataAccessException
    {
        ArrayList<BoardGame> result = new ArrayList<>();
        // Read contents of the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            // Skip the header
            reader.readLine();
            // Inspect this for loop! New way I've seen it work!
            for (String line = reader.readLine(); line != null; line = reader.readLine())
            {
                BoardGame boardGame = deserialize(line);
                if (boardGame != null)
                    result.add(boardGame);
            }
        }
        catch (FileNotFoundException e)
        {
            // Oh well, nothing is there yet
        }
        catch (IOException e)
        {
            throw new DataAccessException(e.getMessage());
        }
        return result;
    }

    @Override
    public BoardGame findByID(int id) throws DataAccessException
    {
        // Let's first get the list of all of our Board Games
        List<BoardGame> boardGames = findAll();
        for (BoardGame boardGame : boardGames)
        {
            if (boardGame.getId() == id)
                return boardGame;
        }
        return null;
    }

    @Override
    public BoardGame findByTitle(String name) throws DataAccessException
    {
        // Let's first get the list of all of our Board Games
        List<BoardGame> boardGames = findAll();
        for (BoardGame boardGame : boardGames)
        {
            if (boardGame.getTitle().equals(name))
                return boardGame;
        }
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

    private BoardGame deserialize(String line)
    {
        String[] values = line.split(",");
        BoardGame boardGame = null;
        // Making sure there are 7 items
        if (values.length == 7)
        {
            boardGame = new BoardGame();
            boardGame.setId(Integer.parseInt(values[0]));
            boardGame.setTitle(values[1]);
            boardGame.setMinimumPlayers(Integer.parseInt(values[2]));
            boardGame.setMaximumPlayers(Integer.parseInt(values[3]));
            boardGame.setRetailReleaseDate(values[4]);
            boardGame.setRating(Double.parseDouble(values[5]));
            // This is how to check for booleans (You could potentially also do parseBoolean)
            boardGame.setInCollection("true".equals(values[6]));
            // This is how to check for Enums
            boardGame.setAvailability(Availability.valueOf(values[7]));
        }
        return boardGame;
    }
}
