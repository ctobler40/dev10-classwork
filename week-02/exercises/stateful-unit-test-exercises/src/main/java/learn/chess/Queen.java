package learn.chess;

/**
 * The most powerful chess piece.
 * Moves horizontally, vertically, or diagonally as many spaces as she wants.
 * She tracks her current position with two integer fields: row and column.
 * Rows and columns are zero-based.
 * The queen starts at row 0 and column 0, though there is no board.
 * Row 7 - - - - - - - -
 * Row 6 - - - - - - - -
 * Row 5 - - - - - - - -
 * Row 4 - - - - - - - -
 * Row 3 - - - - - - - -
 * Row 2 - - - - - - - -
 * Row 1 - - - - - - - -
 * Row 0 Q - - - - - - -
 * Cols: 0 1 2 3 4 5 6 7
 * <p>
 * See: https://en.wikipedia.org/wiki/Queen_(chess)
 */
public class Queen
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
            // The row is the same pos, The col is the same pos, or The row and col move the same
            if (getRow() == row || getColumn() == column || (Math.abs(getRow() - row) == Math.abs(getColumn() - column)))
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
