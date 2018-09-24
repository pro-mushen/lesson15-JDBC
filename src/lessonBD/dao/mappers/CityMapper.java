package lessonBD.dao.mappers;

import lessonBD.pojo.City;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper {
    private static final Logger LOGGER = Logger.getLogger(CityMapper.class);

    public static City getCityFromResult(ResultSet resultSet) {
        City city = null;
        if (resultSet != null) {
            try {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String info = resultSet.getString("more_information");
                city = new City(id, name, info);
            } catch (SQLException e) {
                LOGGER.debug("There were problems in getting the city");
            }
        }
        return city;
    }
}
