package lessonBD.dao;

import lessonBD.connectionManager.ConnectionManager;
import lessonBD.connectionManager.ConnectionManagerJdbcImpl;
import lessonBD.dao.mappers.StudentMapper;
import lessonBD.pojo.Student;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {
    private static final Logger LOGGER = Logger.getLogger(StudentDaoImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
    private StudentMapper studentMapper = new StudentMapper();

    @Override
    public boolean addStudent(Student student) {
        if (student != null) {
            Connection connection = null;
            try {
                connection = connectionManager.getConnectionStudent();
            } catch (SQLException e) {
                return false;
            }
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students VALUES (DEFAULT, ?, ?, ?, ?, ?)")
            ) {
                StudentMapper.setStatementStudent(statement, student);
                statement.execute();
            } catch (SQLException e) {
                LOGGER.debug("There were problems with add " + student);
                return false;
            }
            return true;
        } else {
            LOGGER.info("Student is null");
            return false;
        }
    }

    @Override
    public Student getStudentById(int id) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnectionStudent();
        } catch (SQLException e) {
            return null;
        }
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * from students WHERE id = ?")
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = studentMapper.getStudent(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.debug("There were problems with get student by id = " + id);
            return null;
        }
        return student;
    }

    @Override
    public boolean update(Student student) {
        if (student != null) {
            Connection connection = null;
            try {
                connection = connectionManager.getConnectionStudent();
            } catch (SQLException e) {
                return false;
            }
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET name=?, family_name=?, age=?, contact=?, id_city=? WHERE id=?")
            ) {
                StudentMapper.setStatementStudent(statement, student);
                statement.setInt(6, student.getId());
                statement.execute();
            } catch (SQLException e) {
                LOGGER.debug("There were problems with update student id = " + student.getId());
                return false;
            }
            return true;
        } else {
            LOGGER.info("Student is null");
            return false;
        }
    }

    @Override
    public boolean deleteStudentById(int id) {
        Connection connection = null;
        try {
            connection = connectionManager.getConnectionStudent();
        } catch (SQLException e) {
            return false;
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM students WHERE id=?")
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.debug("There were problems with delete student id = " + id);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteStudentByName(Student student) {
        if (student != null) {
            Connection connection = null;
            try {
                connection = connectionManager.getConnectionStudent();
            } catch (SQLException e) {
                return false;
            }
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM students WHERE name=? and family_name =?")
            ) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getFamilyName());
                statement.execute();
            } catch (SQLException e) {
                LOGGER.debug("There were problems with delete student id = " + student.getId());
                return false;
            }
            return true;
        } else {
            LOGGER.info("Student is null");
            return false;
        }
    }
}
