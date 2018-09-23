package lessonBD.dao;

import lessonBD.connectionManager.ConnectionManager;
import lessonBD.connectionManager.ConnectionManagerJdbcImpl;
import lessonBD.dao.mappers.CityMapper;
import lessonBD.pojo.City;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl implements CityDao {
    private static final Logger LOGGER = Logger.getLogger(CityDaoImpl.class);

    @Override
    public City getCitById(Integer id) {
        City city = null;
        ConnectionManager connectionManagerJdbc = ConnectionManagerJdbcImpl.getInstance();
        Connection connectionCities = null;
        try {
            connectionCities = connectionManagerJdbc.getConnectionStudent();
        } catch (SQLException e) {
            return city;
        }
        try {
            PreparedStatement statement = connectionCities.prepareStatement("SELECT * from cities where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = CityMapper.getCityFromResult(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }
}
