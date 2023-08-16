public class Blank extends GamePiece
{
    private char symbol = ' ';

    public Blank(int x, int y)
    {
        super(x, y);
    }
    public Blank(int x, int y, char symbol)
    {
        super(x, y);
        this.symbol = symbol;
    }
}
