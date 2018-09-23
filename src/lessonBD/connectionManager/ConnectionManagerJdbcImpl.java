package lessonBD.connectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManagerJdbcImpl.class);
    private static final String URL_BD = "jdbc:postgresql://localhost:5501/myFirstDB";
    private static ConnectionManager connectionManager;
    private Connection connectionStudents;

    private ConnectionManagerJdbcImpl() {
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJdbcImpl();
        }
        return connectionManager;
    }

    private void connection() throws SQLException {
        try {
            connectionStudents = DriverManager.getConnection(
                    URL_BD,
                    "postgres",
                    "admin");
        } catch (SQLException e) {
            LOGGER.error("There were problems connecting: " + URL_BD);
            throw new SQLException(URL_BD);
        }
    }

    @Override
    public Connection getConnectionStudent() throws SQLException {
        if (connectionStudents == null) {
            connection();
        }
        try {
            if (connectionStudents.isClosed()) {
                connection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connectionStudents;
    }
}
