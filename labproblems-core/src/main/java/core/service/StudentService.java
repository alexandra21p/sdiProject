package core.service;

import core.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alexandra on 18/04/2017.
 */
public interface StudentService {
    List<Student> findAll();

    Student findStudent(Long studentId);

    Student updateStudent(Long studentId, String serialNumber, String name, Integer group,
                          Set<Long> problems);

    Student addStudent(String serialNumber, String name, Integer group);

    void deleteStudent(Long studentId);

    Student updateStudentGrades(Long studentId, Map<Long, Integer> grades);
}
