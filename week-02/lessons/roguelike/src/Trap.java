public class Trap extends GamePiece
{
    private boolean trap_hitTrap = false;
    private char symbol = 'L';

    public Trap(int x, int y)
    {
        super(x, y);
    }
    public Trap(int x, int y, char symbol)
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

    public boolean getTrapStatus()
    {
        return trap_hitTrap;
    }

    public void setTrapStatus(boolean isFound)
    {
        this.trap_hitTrap = isFound;
    }
}
