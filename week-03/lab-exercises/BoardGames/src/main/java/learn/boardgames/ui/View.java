package learn.boardgames.ui;

public class View
{
    private TextIO io = new ConsoleIO();

    public View(TextIO io)
    {
        this.io = io;
    }

    public void displayHeader(String msg)
    {
        io.println(msg);
        // This is a shortcut to display as long as the given string!
        io.println("=".repeat(msg.length()));
    }
}
