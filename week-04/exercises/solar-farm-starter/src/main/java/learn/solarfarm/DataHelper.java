package learn.solarfarm;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DataHelper
{
    // A DataSource is the thing that allows Java to connect to a database.
    public static DataSource getDataSource()
    {
        // It requires a database URL, a username, and a password --
        // the same things required by MySQL Workbench. Under the hood, a DataSource produces JDBC Connections.
        MysqlDataSource dataSource = new MysqlDataSource();
        // These are environment variables.
        dataSource.setUrl(System.getenv("DB_URL"));
        dataSource.setUser(System.getenv("DB_USERNAME"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));
        return dataSource;
    }

    // JdbcTemplate requires a DataSource,
    // so we add an additional method that creates a JdbcTemplate instance from a DataSource instance.
    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
