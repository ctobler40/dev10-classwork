public class Treasure extends GamePiece
{
    private boolean treasure_hasFound = false;
    private char symbol = 'T';

    public Treasure(int x, int y)
    {
        super(x, y);
    }
    public Treasure(int x, int y, char symbol)
    {
        super(x, y);
        this.symbol = symbol;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public char getSymbol()
    {
        return symbol;
    }

    public boolean getTreasureStatus()
    {
        return treasure_hasFound;
    }

    public void setTreasureStatus(boolean isFound)
    {
        this.treasure_hasFound = isFound;
    }
}
