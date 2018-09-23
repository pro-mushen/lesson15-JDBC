package lessonBD.connectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection getConnectionStudent() throws SQLException;
}
