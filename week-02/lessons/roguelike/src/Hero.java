public class Hero extends GamePiece
{
    private final String name;
    private char symbol = '@';

    // Create a hero with a name and an initial position.
    public Hero(String name, int x, int y)
    {
        super(x, y);
        this.name = name;
    }

    // getters
    public String getName()
    {
        return name;
    }

    public char getSymbol()
    {
        return symbol;
    }
    public void setSymbol(char symbol)
    {
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

    // movement
    public void moveLeft()
    {
        x--;
    }

    public void moveRight()
    {
        x++;
    }

    public void moveUp()
    {
        y--;
    }

    public void moveDown()
    {
        y++;
    }
}
