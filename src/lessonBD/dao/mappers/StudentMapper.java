package lessonBD.dao.mappers;

import lessonBD.dao.CityDao;
import lessonBD.dao.CityDaoImpl;
import lessonBD.pojo.City;
import lessonBD.pojo.Student;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper {
    private static final Logger LOGGER = Logger.getLogger(StudentMapper.class);
    CityDao cityDao = new CityDaoImpl();

    /**
     * Необходимо соблюдать строгую последовательность полей:
     * Name, FamilyName, Age, Contact, City
     */
    public static void setStatementStudent(PreparedStatement statement, Student student) {
        try {
            statement.setString(1, student.getName());
            statement.setString(2, student.getFamilyName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getContact());
            if (student.getCity() != null) {
                statement.setInt(5, student.getCity().getId());
            } else {
                statement.setNull(5, java.sql.Types.INTEGER);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(ResultSet resultSet) {
        try {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String familyName = resultSet.getString("family_name");
            Integer age = resultSet.getInt("age");
            String contact = resultSet.getString("contact");
            int id_city = resultSet.getInt("id_city");
            City city = cityDao.getCitById(id_city);
            Student student = new Student(id, name, familyName, age, contact, city);
            return student;
        } catch (SQLException e) {
            LOGGER.debug("There were problems in getting the student");
        }
        return null;
    }

}
