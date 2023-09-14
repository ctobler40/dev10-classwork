package learn.solarfarm;
import learn.solarfarm.data.SolarPanelFileRepository;
import learn.solarfarm.data.SolarPanelJdbcTemplateRepository;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.ui.ConsoleIO;
import learn.solarfarm.ui.Controller;
import learn.solarfarm.ui.TextIO;
import learn.solarfarm.ui.View;
import org.springframework.jdbc.core.JdbcTemplate;

public class App
{
    public static void main(String[] args)
    {
        JdbcTemplate jdbcTemplate = DataHelper.getJdbcTemplate();

        // repositories
        SolarPanelJdbcTemplateRepository repository = new SolarPanelJdbcTemplateRepository(jdbcTemplate);
        // SolarPanelFileRepository repository = new SolarPanelFileRepository("./data/solarfarm.txt");

        // services
        SolarPanelService service = new SolarPanelService(repository);

        // controller
        TextIO io = new ConsoleIO();
        View view = new View(io);
        Controller controller = new Controller(view, service);

        controller.run();
    }
}

