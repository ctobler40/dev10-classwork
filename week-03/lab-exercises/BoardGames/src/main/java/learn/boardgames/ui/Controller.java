package learn.boardgames.ui;
import learn.boardgames.data.BoardGameRepository;
import learn.boardgames.domain.BoardGameService;

public class Controller
{
    private View view;
    private BoardGameService service;

    public Controller(View view, BoardGameService service)
    {
        this.view = view;
        this.service = service;
    }

    public void run()
    {
        view.displayHeader("Welcome to Board Game Management HQ!");
    }
}
