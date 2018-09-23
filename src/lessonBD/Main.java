package lessonBD;

import lessonBD.dao.StudentDao;
import lessonBD.dao.StudentDaoImpl;
import lessonBD.pojo.Student;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.getStudentById(2);
        studentDao.update(null); // пример вывода в логер
        System.out.println(student);
    }
}
