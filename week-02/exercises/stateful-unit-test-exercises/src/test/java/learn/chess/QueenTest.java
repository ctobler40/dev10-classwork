package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    Queen queen = new Queen();

    @Test
    void shouldMoveToFourCorners() {
        assertTrue(queen.move(7, 0)); // top left;
        assertEquals(7, queen.getRow());
        assertEquals(0, queen.getColumn());

        assertTrue(queen.move(7, 7)); // top right;
        assertEquals(7, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 7)); // bottom right;
        assertEquals(0, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 0)); // bottom left;
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());
    }

    // 1. Add tests to validate Queen movement.
    // Required tests:
    // - anything off the board is invalid, should return false and leave field values alone.
    @Test
    void shouldNotMoveOffBoard()
    {
        assertFalse(queen.move(8, 0));
        assertFalse(queen.move(-1, 0));
        assertFalse(queen.move(0, 8));
        assertFalse(queen.move(0, -1));
    }

    // - moving to the current location should return false and leave field values alone.
    @Test
    void shouldNotMoveToSameSpot()
    {
        assertTrue(queen.move(0, 5));
        assertFalse(queen.move(0, 5));
        assertTrue(queen.move(2, 7));
        assertFalse(queen.move(2, 7));
    }

    // - should still be able to move after an invalid move.
    @Test
    void shouldMoveAfterInvalidMove()
    {
        assertFalse(queen.move(8, 0));
        assertFalse(queen.move(-1, 0));
        assertTrue(queen.move(0, 5));
    }

    // - can move diagonally in various ways
    @Test
    void shouldMoveDiagonally()
    {
        assertTrue(queen.move(5, 5));
        assertTrue(queen.move(7, 3));
        assertTrue(queen.move(4, 0));
        assertTrue(queen.move(0, 4));
    }

    // Always confirm that fields have been properly updated using getters.
}