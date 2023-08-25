package learn.boardgames.data;

import learn.boardgames.models.Availability;
import learn.boardgames.models.BoardGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoardGameFileRepository implements BoardGameRepository {

    private final static String DELIMITER = ",";
    private final static String DELIMITER_REPLACEMENT = "@@@";
    private final static String HEADER = "id,title,minimumPlayers,maximumPlayers,retailReleaseDate,rating,inCollection,availability";

    private final String filePath;

    public BoardGameFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<BoardGame> findAll() throws DataAccessException {
        ArrayList<BoardGame> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                BoardGame boardGame = deserialize(line);
                if (boardGame != null) {
                    result.add(boardGame);
                }
            }

        } catch (FileNotFoundException e) {
            // Oh well, nothing there yet
        } catch (IOException e) {
            throw new DataAccessException(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public BoardGame findById(int id) throws DataAccessException {
        List<BoardGame> boardGames = findAll();

        for (BoardGame boardGame : boardGames) {
            if (boardGame.getId() == id) {
                return boardGame;
            }
        }

        return null;
    }

    @Override
    public List<BoardGame> findByTitle(String title) throws DataAccessException {
        ArrayList<BoardGame> result = new ArrayList<>();
        List<BoardGame> boardGames = findAll();
        for (BoardGame boardGame : boardGames) {
            if (boardGame.getTitle().equalsIgnoreCase(title)) {
                result.add(boardGame);
            }
        }
        return result;
    }

    @Override
    public BoardGame add(BoardGame boardGame) throws DataAccessException {
        List<BoardGame> boardGames = findAll();
        int nextId = getNextId(boardGames);
        boardGame.setId(nextId);
        boardGames.add(boardGame);
        writeToFile(boardGames);
        return boardGame;
    }

    @Override
    public boolean update(BoardGame boardGame) throws DataAccessException {
        List<BoardGame> boardGames = findAll();
        for (int i = 0; i < boardGames.size(); i++) {
            if (boardGames.get(i).getId() == boardGame.getId()) {
                boardGames.set(i, boardGame);
                writeToFile(boardGames);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        List<BoardGame> boardGames = findAll();
        for(int i = 0; i < boardGames.size(); i++) {
            BoardGame boardGame = boardGames.get(i);
            if (boardGame.getId() == id) {
                boardGames.remove(i);
                writeToFile(boardGames);
                return true;
            }
        }

        return false;
    }

    private void writeToFile(List<BoardGame> boardGames) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (BoardGame boardGame : boardGames) {
                writer.println(serialize(boardGame));
            }
        } catch (IOException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    private String serialize(BoardGame boardGame) {
        return boardGame.getId() + DELIMITER +
                cleanString(boardGame.getTitle()) + DELIMITER +
                boardGame.getMinimumPlayers() + DELIMITER +
                boardGame.getMaximumPlayers() + DELIMITER +
                boardGame.getRetailReleaseDate() + DELIMITER +
                boardGame.getRating() + DELIMITER +
                boardGame.isInCollection() + DELIMITER +
                boardGame.getAvailability();
    }

    private String cleanString(String value) {
        return value.replace(DELIMITER, DELIMITER_REPLACEMENT)
                .replace("/r", "")
                .replace("/n", "");
    }

    private BoardGame deserialize(String line) {
        BoardGame boardGame = null;
        String[] values = line.split(DELIMITER);
        if (values.length == 8) {
            boardGame = new BoardGame();
            boardGame.setId(Integer.parseInt(values[0]));
            boardGame.setTitle(addDelimiter(values[1]));
            boardGame.setMinimumPlayers(Integer.parseInt(values[2]));
            boardGame.setMaximumPlayers(Integer.parseInt(values[3]));
            boardGame.setRetailReleaseDate(values[4]);
            boardGame.setRating(Double.parseDouble(values[5]));
            boardGame.setInCollection("true".equals(values[6]));
            boardGame.setAvailability(Availability.valueOf(values[7]));
        }
        return boardGame;
    }

    private String addDelimiter(String value) {
        return value.replace(DELIMITER_REPLACEMENT, DELIMITER);
    }

    private int getNextId(List<BoardGame> boardGames) throws DataAccessException {
        int maxId = 0;
        for (BoardGame boardGame: boardGames) {
            if (boardGame.getId() > maxId) {
                maxId = boardGame.getId();
            }
        }
        return maxId + 1;
    }
}
