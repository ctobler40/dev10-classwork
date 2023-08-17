package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest
{
    Knight knight = new Knight();

    @Test
    void shouldNotMoveToUnreachablePositions()
    {
        assertFalse(knight.move(7, 0)); // top left;
        assertFalse(knight.move(7, 7)); // top right;
        assertFalse(knight.move(0, 7)); // bottom right;
        assertFalse(knight.move(0, 0)); // bottom left;
    }

    @Test
    void shouldNotMoveToImpossiblePositions()
    {
        assertFalse(knight.move(8, 0));
        assertFalse(knight.move(-1, 0));
        assertFalse(knight.move(0, 8));
        assertFalse(knight.move(0, -1));
    }

    @Test
    void shouldMoveToDuplicateSpaces()
    {
        assertTrue(knight.move(2, 1));
        assertFalse(knight.move(2, 1));
        assertTrue(knight.move(3, 3));
        assertFalse(knight.move(3, 3));
    }

    @Test
    void knightFreeMovement()
    {
        assertTrue(knight.move(2, 1));
        assertTrue(knight.move(3, 3));
        assertTrue(knight.move(1, 4));
        assertTrue(knight.move(3, 5));
        assertTrue(knight.move(2, 7));
        assertTrue(knight.move(4, 6));
        assertTrue(knight.move(5, 4));
        assertTrue(knight.move(7, 5));
    }
}