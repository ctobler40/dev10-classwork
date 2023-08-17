package learn.chess;

/**
 # Exercise03

 1. Create a new class `learn.chess.Knight`.
 2. Model its location with integer `row` and `column` fields.
 3. Add getters and a `move` method. (See Exercise02.)
 4. Generate tests for `Knight`.
 5. Complete the `move` method and confirm it's correct with tests.
 See https://en.wikipedia.org/wiki/Knight_(chess) for the Knight's movement rules.
 */
public class Knight
{
    private int row = 0;
    private int column = 0;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int newRow) {
        row = newRow;
    }

    public void setColumn(int newColumn) {
        column = newColumn;
    }

    /**
     * Requests a move to a new row and column.
     *
     * @param row    the requested row
     * @param column the requested column
     * @return true if the move is valid, false if it's not
     */
    public boolean move(int row, int column)
    {
        // 1. Implement the move method.
        // If the move is valid, return true and update `row` and `column` field
        // First make sure the row and column are between 0 and 7 and not a duplicate
        if (row > -1 && column > -1 && row < 8 && column < 8)
        {
            // Make sure they aren't in the same space
            if (getRow() == row && getColumn() == column)
            {
                return false;
            }
            // Return true if..
            // abs value of row = 2 && abs value of col == 1 OR
            // abs value of col = 2 && abs value of row == 1
            if ((Math.abs(getRow() - row) == 2 && (Math.abs(getColumn() - column) == 1)) ||
                (Math.abs(getRow() - row) == 1 && (Math.abs(getColumn() - column) == 2)))
            {
                setRow(row);
                setColumn(column);
                return true;
            }
        }
        // If the move is invalid, return false and do not update fields.
        return false;
    }
}
