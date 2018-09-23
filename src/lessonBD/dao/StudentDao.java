package lessonBD.dao;

import lessonBD.pojo.Student;

public interface StudentDao {
    boolean addStudent(Student student);

    Student getStudentById(int id);

    boolean update(Student student);

    boolean deleteStudentById(int id);

    boolean deleteStudentByName(Student student);
}
